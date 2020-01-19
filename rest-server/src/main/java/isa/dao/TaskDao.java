package isa.dao;

import isa.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Local
public class TaskDao {


	private Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;

	public long createNew(Task task) {
		em.persist(task);
		return task.getId();
	}

	public Task save(Task task) {
		em.merge(task);
		return task;
	}

	public Task getById(Long id) {
		return em.find(Task.class, id);
	}

	public List<Task> findActiveByDeveloper(Long developerId) {
		List list = this.em
			.createQuery("SELECT t FROM Task t INNER JOIN t.developers dev WHERE t.closed=false AND dev.id = :developerId")
			.setParameter("developerId", developerId)
			.getResultList();
		log.debug("Found {} tasks.", list.size());
		return list;
	}

}
