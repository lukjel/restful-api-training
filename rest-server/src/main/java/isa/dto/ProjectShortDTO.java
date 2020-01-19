package isa.dto;

public class ProjectShortDTO {

	private Long id;
	private String name;

	public ProjectShortDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
