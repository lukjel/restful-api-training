package isa.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import isa.dao.UserDao;
import isa.domain.User;
import isa.dto.UserDTO;
import isa.exceptions.AuthorizationException;
import isa.mapper.UserMapper;
import isa.util.TokenUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;

@Stateless
public class UserService {


	Logger log = LoggerFactory.getLogger(getClass());
	//FIXME dodaj Algorytm i Verifier do JWT.

	@Inject
	UserDao userDao;

	@Inject
	UserMapper mapper;

	public String createUser(String login, String password) {
		//FIXME
		return null;
	}

	private String createNewJWT(User account) {
		//FIXME
		return null;
	}

	public String login(String login, String password) throws AuthorizationException {
		//FIXME
		return null;
	}

	public UserDTO verifyJwt(String token) throws AuthorizationException {
		//FIXME
		return null;
	}


	public String updateJwt(String token) {
		//FIXME
		return null;
	}
}
