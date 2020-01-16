package isa.hibernate.dto;

import java.util.List;

public class ProjectFullDTO {

	private Long id;
	private String name;
	private List<TaskFullDTO> tasks;

	public ProjectFullDTO(Long id, String name, List<TaskFullDTO> tasks) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<TaskFullDTO> getTasks() {
		return tasks;
	}
}
