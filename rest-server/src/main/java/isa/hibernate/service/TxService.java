package isa.hibernate.service;

import isa.hibernate.exceptions.SomethingWentWrongException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TxService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private TaskService taskService;
	@Inject
	private DeveloperService developerService;

	public void closeTaskWithTx(Long taskId) {
		taskService.breakTask(taskId);
		taskService.closeTask(taskId);
		throw new SomethingWentWrongException();
	}

	public void changeNameWithTx(Long devId, String city) {
		developerService.changeCity(devId, city);
		throw new SomethingWentWrongException();
	}
}
