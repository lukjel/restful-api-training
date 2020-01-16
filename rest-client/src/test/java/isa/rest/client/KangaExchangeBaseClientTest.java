package isa.rest.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;

import static org.junit.Assert.*;

public class KangaExchangeBaseClientTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void pingString() {
		KangaExchangeBaseClient client = new KangaExchangeBaseClient();
		String response = client.pingString();
		log.debug("Response: {}", response);
		assertNotNull("No response!", response);
	}
}