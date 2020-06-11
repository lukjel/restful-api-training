package isa.hibernate.dao;

import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DeveloperDao {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@PersistenceContext
	private EntityManager em;

	public Developer getById(Long id) {
		return this.em.find(Developer.class, id);
	}

	public List<Developer> find() {
		List list = this.em
			.createNamedQuery("Developer.findAll")
			.getResultList();
		return list;
	}

	public void save(Developer dev) {
		this.em.merge(dev);
	}

	public long createNew(Developer dev) {
		this.em.persist(dev);
		return dev.getId();
	}

	public void removeActive(Task task) {
		this.em
			.createQuery("UPDATE Developer d SET d.activeTask=null WHERE d.activeTask=:task")
			.setParameter("task", task)
			.executeUpdate();
	}
}
