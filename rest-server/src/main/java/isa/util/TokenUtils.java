package isa.util;

import java.security.SecureRandom;
import java.util.zip.CRC32;

public class TokenUtils {
	private static final SecureRandom random = new SecureRandom();
	private static final char[] CHARS = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};

	public static String signedToken(int size) {
		return signed(unsignedToken(size));
	}

	private static String signed(String text) {
		int code = text.hashCode();
		char[] chars = new char[4];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = CHARS[(code & 0x7F) % (CHARS.length - 1)];
			code >>>= 5;
		}
		return text + new String(chars);
	}

	public static String valid(String signed) {
		if (signed == null || signed.length() < 5) {
			throw new RuntimeException("checksum failed");
		}
		final String token = signed.substring(0, signed.length() - 4);
		final String resigned = signed(token);
		if (signed.equals(resigned)) {
			return signed;
		}
		throw new RuntimeException("checksum failed");
	}

	private static String unsignedToken(int size) {
		if (size < 5) {
			throw new IllegalArgumentException("size too small: " + size);
		}
		byte[] bytes = new byte[size - 4];
		synchronized (random) {
			random.nextBytes(bytes);
		}
		char[] chars = new char[size - 4];
		for (int i = 0; i < bytes.length; i++) {
			chars[i] = CHARS[(bytes[i] & 0x7F) % (CHARS.length - 1)];
		}
		return new String(chars);
	}

	public static String crc32(String base) {
		CRC32 crc = new CRC32();
		crc.update(base.getBytes());
		return Long.toHexString(crc.getValue());
	}

}
