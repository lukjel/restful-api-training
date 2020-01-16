package isa.hibernate.service;

import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Project;
import isa.hibernate.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RemoveService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@PersistenceContext
	private EntityManager em;
	@Inject
	private TaskService taskService;

	public void rmProject(Long projectId) {
		Project p = em.find(Project.class, projectId);

		em
			.createNativeQuery("UPDATE developer d JOIN task t ON d.id_active_task=t.id SET d.id_active_task=null WHERE t.project_id=:pid")
			.setParameter("pid", p.getId())
			.executeUpdate();

		em
			.createNativeQuery("DELETE FROM Task t WHERE t.project_id=:pid")
			.setParameter("pid", p.getId())
			.executeUpdate();

		em.remove(p);
	}

	public void rmDeveloper(Long devId) {
		Developer d = em.find(Developer.class, devId);
		em.remove(d);
	}

	public void rmTask(Long id) {
		Task t = em.find(Task.class, id);
		em.remove(t);
	}
}
