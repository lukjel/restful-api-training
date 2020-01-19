package isa.dao;

import isa.domain.User;
import isa.exceptions.CreateUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDao {

	Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	EntityManager em;

	public void createNew(User account) {
		try {
			em.persist(account);
		} catch (Exception ex) {
			log.debug("Some exception: {}", ex.getClass());
			throw new CreateUserException();
		}

	}

	public User findByLogin(String login) {
		List<User> resultList = em
			.createQuery("SELECT u FROM User u WHERE u.login=:login")
			.setParameter("login", login)
			.getResultList();
		if (resultList.size() == 0) {
			return null;
		}
		return resultList.get(0);
	}

	public User findByToken(String userToken) {
		List<User> resultList = em
			.createQuery("SELECT u FROM User u WHERE u.token=:token")
			.setParameter("token", userToken)
			.getResultList();
		if (resultList.size() == 0) {
			return null;
		}
		return resultList.get(0);
	}
}
