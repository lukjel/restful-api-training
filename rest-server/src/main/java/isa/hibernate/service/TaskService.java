package isa.hibernate.service;

import isa.hibernate.dao.DeveloperDao;
import isa.hibernate.dao.ProjectDao;
import isa.hibernate.dao.TaskDao;
import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Project;
import isa.hibernate.domain.Task;
import isa.hibernate.dto.TaskFullDTO;
import isa.hibernate.exceptions.AlreadyActiveException;
import isa.hibernate.exceptions.AlreadyClosedException;
import isa.hibernate.exceptions.WorkNotFinishedException;
import isa.hibernate.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class TaskService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private TaskDao taskDao;

	@Inject
	private ProjectDao projectDao;

	@Inject
	private DeveloperDao developerDao;

	@Inject
	private TaskMapper taskMapper;

	public long newTask(String title, long projectId) {
		Project project = projectDao.getById(projectId);
		Task task = new Task();
		task.setClosed(false);
		task.setProject(project);
		task.setTitle(title);
		taskDao.save(task);
		return task.getId();
	}

	public TaskFullDTO getDetailsDTO(long taskId) {
		Task task = taskDao.getById(taskId);
		return taskMapper.toFull(task);
	}

	public JsonObject getFullJson(long taskId) {
		Task task = taskDao.getById(taskId);
		return taskMapper.toFullJson(task);
	}

	public JsonObject getShortJson(Long id) {
		Task task = taskDao.getById(id);
		return taskMapper.toShortJson(task);
	}

	public Task getById(Long id) {
		return this.taskDao.getById(id);
	}

	public void closeTask(Long id) throws AlreadyClosedException, WorkNotFinishedException {
		Task task = taskDao.getById(id);
		if (task.isClosed()) throw new AlreadyClosedException();
		Optional<Developer> developerOptional = task.getDevelopers().stream()
			.filter(d -> d.getActiveTask() == task)
			.findFirst();
		if (developerOptional.isPresent()) {
			throw new WorkNotFinishedException();
		}
		task.setClosed(true);
		taskDao.save(task);
	}

	public void breakTask(Long id) throws AlreadyClosedException {
		Task task = taskDao.getById(id);
		if (task.isClosed()) throw new AlreadyClosedException();
		task.getDevelopers().stream()
			.filter(d -> d.getActiveTask() == task)
			.forEach(d -> {
				d.setActiveTask(null);
			});
		taskDao.save(task);
	}

	public List<JsonObject> findActiveByDeveloper(Long developerId) {
		List<Task> tasks = taskDao.findActiveByDeveloper(developerId);
		return tasks.stream()
			.map(t -> taskMapper.toShortJson(t))
			.collect(Collectors.toList());
	}

	public boolean assignTask(Long devId, Long taskId) {
		Developer dev = developerDao.getById(devId);
		Task task = taskDao.getById(taskId);
		if (task.addDeveloper(dev)) {
			taskDao.save(task);
			return true;
		}
		return false;
	}

	public boolean unassignTask(Long devId, Long taskId) {
		Task task = taskDao.getById(taskId);
		Developer dev = developerDao.getById(devId);
		if (task.removeDeveloper(dev)) {
			taskDao.save(task);
			return true;
		}
		return false;
	}

	public void openTask(Long taskId) throws AlreadyActiveException {
		Task task = taskDao.getById(taskId);
		if (!task.isClosed()) {
			throw new AlreadyActiveException();
		}
		task.setClosed(false);
		taskDao.save(task);
	}
}
