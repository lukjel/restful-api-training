package isa.hibernate.service;

import isa.hibernate.dao.DeveloperDao;
import isa.hibernate.dao.ProjectDao;
import isa.hibernate.dao.TaskDao;
import isa.hibernate.domain.Developer;
import isa.hibernate.domain.Project;
import isa.hibernate.domain.Task;
import isa.hibernate.util.DeveloperCreator;
import isa.hibernate.util.ProjectCreator;
import isa.hibernate.util.Response;
import isa.hibernate.util.TaskCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Stateless
public class GeneratorService {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private Random rnd = new Random();

	@Inject
	private TaskDao taskDao;
	@Inject
	private ProjectDao projectDao;
	@Inject
	private DeveloperDao developerDao;

	public JsonObject generate(long devCnt, long projCnt, long taskCnt) {
		List<Developer> devs = new ArrayList<>();
		List<Project> projects = new ArrayList<>();
		for (int i = 0; i < devCnt; i++) {
			Developer d = DeveloperCreator.generate();
			long id = developerDao.createNew(d);
			logger.debug("Developer id {}", id);
			devs.add(d);
		}
		for (int i = 0; i < projCnt; i++) {
			Project p = ProjectCreator.generate();
			long id = projectDao.createNew(p);
			logger.debug("Project id {}", id);
			projects.add(p);
		}
		for (int i = 0; i < taskCnt; i++) {
			Task task = TaskCreator.generate();
			task.setProject(projects.get(rnd.nextInt(projects.size())));
			int developers = rnd.nextInt(8) + 1;
			for (int d = 0; d < developers; d++) {
				task.addDeveloper(devs.get(rnd.nextInt(devs.size())));
			}
			long id = taskDao.createNew(task);
			logger.debug("Task id {}", id);
		}
		return Response.ok("developers", devs.size(), "projects", projects.size());

	}
}
