package isa.hibernate.mapper;

import isa.hibernate.domain.Task;
import isa.hibernate.dto.DeveloperShortDTO;
import isa.hibernate.dto.ProjectShortDTO;
import isa.hibernate.dto.TaskFullDTO;
import isa.hibernate.dto.TaskShortDTO;
import isa.hibernate.util.JsonHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;

@Stateless
public class TaskMapper {

	@Inject
	private DeveloperMapper userMapper;
	@Inject
	private ProjectMapper projectMapper;

	public TaskFullDTO toFull(Task task) {
		if (task == null) {
			return null;
		}
		DeveloperShortDTO[] devs = task.getDevelopers().stream()
			.map(d -> userMapper.toShort(d))
			.toArray(DeveloperShortDTO[]::new);
		ProjectShortDTO project = projectMapper.toShort(task.getProject());
		Long projectId = task.getProject() == null ? null : task.getProject().getId();
		String projectName = task.getProject() == null ? null : task.getProject().getName();
		return new TaskFullDTO(
			task.getId(),
			task.getTitle(),
			task.isClosed(),
			project,
			devs
		);
	}

	public TaskShortDTO toShort(Task t) {
		if (t == null) {
			return null;
		}
		return new TaskShortDTO(
			t.getId(),
			t.getTitle(),
			t.isClosed(),
			projectMapper.toShort(t.getProject())
		);
	}

	public JsonObject toFullJson(Task t) {
		if (t == null) {
			return null;
		}
		return JsonHelper.toJson(
			"id", t.getId(),
			"title", t.getTitle(),
			"closed", t.isClosed(),
			"project", projectMapper.toShortJson(t.getProject()),
			"developers", JsonHelper.toArray(t.getDevelopers().stream()
				.map(d -> userMapper.toShortJson(d))
				.toArray()));
	}

	public JsonObject toShortJson(Task t) {
		if (t == null) {
			return Json.createObjectBuilder().build();
		}
		return JsonHelper.toJson(
			"id", t.getId(),
			"title", t.getTitle(),
			"closed", t.isClosed(),
			"project", projectMapper.toShortJson(t.getProject()));

	}

}
