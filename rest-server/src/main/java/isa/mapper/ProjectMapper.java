package isa.mapper;

import isa.domain.Project;
import isa.domain.custom.ProjectReport;
import isa.dto.ProjectFullDTO;
import isa.dto.ProjectShortDTO;
import isa.util.JsonHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.stream.Collectors;

@Stateless
public class ProjectMapper {

	@Inject
	private TaskMapper taskMapper;

	public ProjectShortDTO toShort(Project project) {
		return new ProjectShortDTO(project.getId(), project.getName());
	}

	public ProjectFullDTO toFull(Project project) {
		return new ProjectFullDTO(
			project.getId(),
			project.getName(),
			project.getTasks().stream()
				.map(t -> taskMapper.toFull(t))
				.collect(Collectors.toList())
		);
	}

	public JsonObject toFullJson(Project project) {
		return JsonHelper.toJson(
			"id", project.getId(),
			"name", project.getName(),
			"tasks", JsonHelper.toArray(project.getTasks().stream()
				.map(t -> taskMapper.toFullJson(t))
				.toArray()));
	}

	public JsonObject toShortJson(Project project) {
		if (project == null) {
			return null;
		}
		return JsonHelper.toJson(
			"id", project.getId(),
			"name", project.getName());
	}

	public JsonObject toShortJson(ProjectReport report) {
		if (report == null) {
			return null;
		}
		return JsonHelper.toJson(
			"id", report.getProjectId(),
			"count", report.getCnt()
		);
	}
}
