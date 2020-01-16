package isa.hibernate.service;

import isa.hibernate.exceptions.SomethingWentWrongException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TxBeanService {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private UserTransaction txn;
	@Inject
	private TaskService taskService;
	@Inject
	private DeveloperService developerService;

	public void closeTask(Long taskId) {
		try {
			txn.begin();
			taskService.breakTask(taskId);
			taskService.closeTask(taskId);
			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (SystemException e) {
				logger.debug("Very bad!");
			}
		}
	}

	public void changeNameWithTx(Long devId, String city) {
		try {
			txn.begin();
			developerService.changeCity(devId, city);
			throw new SomethingWentWrongException();
//			txn.commit();
		} catch (Exception ex) {
			try {
				txn.rollback();
			} catch (SystemException e) {
				logger.debug("Very bad!");
			}
		}
	}
}
