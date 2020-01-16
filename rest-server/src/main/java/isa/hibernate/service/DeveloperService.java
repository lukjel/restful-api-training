package isa.hibernate.service;

import isa.hibernate.dao.DeveloperDao;
import isa.hibernate.dao.TaskDao;
import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Task;
import isa.hibernate.dto.DeveloperFullDTO;
import isa.hibernate.dto.DeveloperShortDTO;
import isa.hibernate.exceptions.ActivateTaskException;
import isa.hibernate.mapper.DeveloperMapper;
import isa.hibernate.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.List;

@Stateless
public class DeveloperService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private DeveloperDao developerDao;
	@Inject
	private TaskDao taskDao;

	@Inject
	private DeveloperMapper devMapper;

	public long addDeveloper(String firstName, String lastName, String city) {
		Developer dev = new Developer();
		dev.setFirstName(firstName);
		dev.setLastName(lastName);
		dev.setCity(city);
		return this.developerDao.createNew(dev);
	}

	public long addDeveloper(DeveloperShortDTO dto) {
		Developer dev = devMapper.toEntity(dto);
		developerDao.createNew(dev);
		return dev.getId();
	}

	public DeveloperFullDTO getByIdFullDTO(Long id) {
		Developer d = this.developerDao.getById(id);
		return devMapper.toFull(d);
	}

	public JsonArray findAllJson() {
		List<Developer> devs = developerDao.find();
		return JsonHelper.toArray(devs.stream()
			.map(d -> devMapper.toShortJson(d))
			.toArray());
	}

	public Developer getById(Long id) {
		return this.developerDao.getById(id);
	}

	public void setActiveTask(Long devId, Long taskId) throws ActivateTaskException {
		logger.debug("Make task {} active for developer {}", taskId, devId);
		logger.debug("Read Task");
		Task t = taskDao.getById(taskId);
		logger.debug("Get Developer");
		Developer d = developerDao.getById(devId);
		logger.debug("Has developer this task");
		if (!d.getTasks().contains(t)) {
			// Is this rly needed? Rollback is EXPENSIVE
			throw new ActivateTaskException();
		}
		logger.debug("Set Developer active task");
		d.setActiveTask(t);
		logger.debug("Save Developer");
		developerDao.save(d);
		logger.debug("Done");
	}

	public void unassignDeveloper(Long devId) {
		Developer d = developerDao.getById(devId);
		d.getTasks().stream()
			.filter(t -> !t.isClosed())
			.forEach(t -> {
				t.removeDeveloper(d);
				taskDao.save(t);
			});
//		developerDao.save(d);
	}


	public JsonObject getByIdFullJson(Long id) {
		return devMapper.toFullJson(getById(id));
	}

	public void changeCity(Long devId, String newCity) {
		Developer dev = developerDao.getById(devId);
		dev.setCity(newCity);
		developerDao.save(dev);
	}

	public void changeCityLong(Long devId, String newCity) {
		Developer dev = developerDao.getById(devId);
		dev.setCity(newCity);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// don't care
		}
		developerDao.save(dev);
	}

}
