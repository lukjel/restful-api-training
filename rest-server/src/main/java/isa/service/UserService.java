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
	Algorithm algo = Algorithm.HMAC512("some very long secret phrase! Shouldn't be in the code but loaded from the config file provided by config provider");
	JWTVerifier jwtVerifier = JWT.require(this.algo)
		.withIssuer("infoShareAcademy")
		.build();

	@Inject
	UserDao userDao;

	@Inject
	UserMapper mapper;

	public String createUser(String login, String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(7));
		User account = new User();
		account.setLogin(login);
		account.setPassword(hash);
		account.setRole("user");
		String token = TokenUtils.signedToken(40);
		account.setToken(token);
		userDao.createNew(account);
		String jwt = createNewJWT(account);
		return jwt;
	}

	private String createNewJWT(User account) {
		String token = JWT.create()
			.withClaim("userToken", account.getToken())
			.withExpiresAt(Date.from(Instant.now().plusSeconds(15 * 60)))
			.withIssuer("infoShareAcademy")
			.sign(algo);
		return token;
	}

	public String login(String login, String password) throws AuthorizationException {
		User account = userDao.findByLogin(login);
		if (account == null) {
			throw new AuthorizationException();
		}
		String hash = account.getPassword();
		boolean ok = BCrypt.checkpw(password, hash);
		if (!ok) {
			throw new AuthorizationException();
		}
		return createNewJWT(account);
	}

	public UserDTO verifyJwt(String token) throws AuthorizationException {
		try {
			log.debug("Check JWT");
			DecodedJWT jwt = jwtVerifier.verify(token);
			String userToken = jwt.getClaim("userToken").asString();
			log.debug("JWT is properly signed. Try to find user with token: {}", userToken);
			User account = userDao.findByToken(userToken);
			if (account == null) {
				log.debug("No user!");
				throw new AuthorizationException();
			}
			log.debug("User found. Proceed...");
			UserDTO dto = mapper.toDTO(account);
			return dto;
		} catch (JWTVerificationException e) {
			log.debug("JWT is not proper one! {}", e.getMessage());
			throw new AuthorizationException();
		}
	}


	public String updateJwt(String token) {
		DecodedJWT jwt = JWT.decode(token);
		String newToken = JWT.create()
			.withClaim("userToken", jwt.getClaim("userToken").asString())
			.withExpiresAt(Date.from(Instant.now().plusSeconds(15 * 60)))
			.withIssuer("infoShareAcademy")
			.sign(algo);
		return newToken;
	}
}
