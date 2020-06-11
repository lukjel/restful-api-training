package isa.hibernate.dto;

public class TaskFullDTO {

	private final ProjectShortDTO project;
	private Long id;
	private String title;
	private boolean closed;
	private Long projectId;
	private String projectName;
	private DeveloperShortDTO[] developers;

	public TaskFullDTO(Long id, String title, boolean closed, ProjectShortDTO project, DeveloperShortDTO[] developers) {
		this.id = id;
		this.title = title;
		this.closed = closed;
		this.project = project;
		this.developers = developers;
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

	public DeveloperShortDTO[] getDevelopers() {
		return developers;
	}

}
