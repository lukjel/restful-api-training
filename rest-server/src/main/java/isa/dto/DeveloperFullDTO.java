package isa.dto;

import java.util.Set;

public class DeveloperFullDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String city;
	private TaskShortDTO activeTask;
	private Set<TaskShortDTO> tasks;

	public DeveloperFullDTO(Long id, String firstName, String lastName, String city, TaskShortDTO activeTask, Set<TaskShortDTO> tasks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.activeTask = activeTask;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public TaskShortDTO getActiveTask() {
		return activeTask;
	}

	public Set<TaskShortDTO> getTasks() {
		return tasks;
	}
}

