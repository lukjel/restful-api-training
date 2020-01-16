package isa.hibernate.dto;

import isa.hibernate.domain.Developer;

public class DeveloperShortDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String city;

	public DeveloperShortDTO() {

	}

	public DeveloperShortDTO(Long id, String firstName, String lastName, String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
