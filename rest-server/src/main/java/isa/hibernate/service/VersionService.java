package isa.hibernate.service;

import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class VersionService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private DeveloperService developerService;

	@PersistenceContext
	private EntityManager em;

	public BigDecimal incLong(Long developerId, BigDecimal amount) {
		Developer dev = developerService.getById(developerId);
		List<Wallet> walletList = em
			.createQuery("SELECT w FROM Wallet w WHERE w.owner=:developer")
			.setParameter("developer", dev)
			.getResultList();
		Wallet w = walletList.size() == 0 ? newWallet(dev) : walletList.get(0);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			logger.debug("Ups... interrupted");
		}
		w.inc(amount);
		em.merge(w);
		return w.getBalance();
	}

	public BigDecimal inc(Long developerId, BigDecimal amount) {
		Developer dev = developerService.getById(developerId);
		List<Wallet> walletList = em
			.createQuery("SELECT w FROM Wallet w WHERE w.owner=:developer")
			.setParameter("developer", dev)
			.getResultList();
		Wallet w = walletList.size() == 0 ? newWallet(dev) : walletList.get(0);
		w.inc(amount);
		em.merge(w);
		return w.getBalance();
	}

	private Wallet newWallet(Developer dev) {
		Wallet w = new Wallet(dev);
		this.em.persist(w);
		return w;
	}
}
