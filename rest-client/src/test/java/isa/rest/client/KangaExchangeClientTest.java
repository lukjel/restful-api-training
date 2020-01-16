package isa.rest.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class KangaExchangeClientTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testPingWithTargets() {
		KangaExchangeClient client = new KangaExchangeClient();
		String response = client.pingWithTargets();
		log.debug("Response: {}", response);
		assertTrue("No ping", response.contains("pong"));
	}
}