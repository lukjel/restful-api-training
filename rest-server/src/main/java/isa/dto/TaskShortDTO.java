package isa.dto;

public class TaskShortDTO {

	private final ProjectShortDTO project;
	private Long id;
	private String title;
	private boolean closed;
	private Long projectId;
	private String projectName;

	public TaskShortDTO(Long id, String title, boolean closed, ProjectShortDTO project) {
		this.id = id;
		this.title = title;
		this.closed = closed;
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isClosed() {
		return closed;
	}

	public ProjectShortDTO getProject() {
		return project;
	}

}
