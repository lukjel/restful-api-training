package isa.mapper;

import isa.domain.User;
import isa.dto.UserDTO;

import javax.ejb.Stateless;

@Stateless
public class UserMapper {


	public UserDTO toDTO(User acc) {
		return new UserDTO(
			acc.getId(),
			acc.getLogin(),
			acc.getToken(),
			acc.getRole()
		);
	}
}
