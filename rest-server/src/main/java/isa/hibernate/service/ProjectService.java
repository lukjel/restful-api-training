package isa.hibernate.service;

import isa.hibernate.dao.ProjectDao;
import isa.hibernate.domain.Project;
import isa.hibernate.dto.ProjectFullDTO;
import isa.hibernate.mapper.ProjectMapper;
import isa.hibernate.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

@Stateless
public class ProjectService {

	private Logger log = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private ProjectDao projectDao;
	@Inject
	private ProjectMapper projectMapper;

	public long newProject(String name) {
		Project p = new Project();
		p.setName(name);
		projectDao.createNew(p);
		return p.getId();
	}

	public Project getById(Long id) {
		return this.projectDao.getById(id);
	}

	public ProjectFullDTO getFullDTO(Long id) {
		return projectMapper.toFull(getById(id));
	}

	public JsonObject getFullJson(Long id) {
		return projectMapper.toFullJson(getById(id));
	}

	public JsonArray report() {
		return JsonHelper.toArray(projectDao.report()
			.stream()
			.map(r -> projectMapper.toShortJson(r))
			.toArray());
	}

	public JsonArray findWithOpen() {
		return JsonHelper.toArray(projectDao.projectWithOpenTasks()
			.stream()
			.map(p -> projectMapper.toShortJson(p))
			.toArray());
	}
}
