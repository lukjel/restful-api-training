package isa.dto;

public class UserDTO {

	Long id;

	String login;

	String token;

	String role;

	public UserDTO(Long id, String login, String token, String role) {
		this.id = id;
		this.login = login;
		this.token = token;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getToken() {
		return token;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "AccountDTO{" +
			"id=" + id +
			", login='" + login + '\'' +
			", token='" + token + '\'' +
			", role='" + role + '\'' +
			'}';
	}
}
