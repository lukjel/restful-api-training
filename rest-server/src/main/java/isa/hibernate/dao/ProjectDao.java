package isa.hibernate.dao;

import isa.hibernate.domain.Project;
import isa.hibernate.domain.custom.ProjectReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local
public class ProjectDao {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;

	public long createNew(Project project) {
		em.persist(project);
		return project.getId();
	}

	public Project save(Project project) {
		em.merge(project);
		return project;
	}

	public Project getById(Long id) {
		return em.find(Project.class, id);
	}

	public List<ProjectReport> report() {
		List<Object[]> resultList = em
			.createNativeQuery("SELECT p.id, count(*) as cnt FROM project p LEFT JOIN task t on t.project_id=p.id and t.closed=false GROUP BY p.id")
			.getResultList();
		log.debug("Result list size: {}", resultList.size());
		log.debug("First element: {}", resultList.get(0));
		log.debug("First element type: {}", resultList.get(0).getClass());
		List<ProjectReport> report = resultList
			.stream()
			.map(o -> new ProjectReport((BigInteger) o[0], (BigInteger) o[1]))
			.collect(Collectors.toList());
		return report;
	}

	public List<Project> projectWithOpenTasks() {
		List<Project> list = this.em
			.createQuery("SELECT distinct(p) FROM Project p INNER JOIN p.tasks t WHERE t.closed=false")
			.getResultList();
		log.debug("Found {} projects.", list.size());
		return list;
	}
}
