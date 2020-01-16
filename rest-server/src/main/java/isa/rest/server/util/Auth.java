package isa.rest.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Auth {

	private static final Logger log = LoggerFactory.getLogger(Auth.class);
	private static final String ALGO = "HmacSHA512";

	public static String sign(String data, String secret) {
		SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), ALGO);
		try {
			Mac mac = Mac.getInstance(ALGO);
			mac.init(keySpec);
			byte[] bytes = mac.doFinal(secret.getBytes());
			Formatter f = new Formatter();
			for (Byte b : bytes) {
				f.format("%02x", b);
			}
			return f.toString();
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			log.info("Some error during ENCODE", e);
		}
		return "";
	}
}
