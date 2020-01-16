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

	@Test
	public void testMarketDetailsStringWithString() {
		KangaExchangeClient client = new KangaExchangeClient();
		String response = client.marketDetailsStringWithString("ETH-BTC");
		log.debug("Response: {}", response);
		assertTrue("No ping", response.contains("payingCurrency"));
	}

	@Test
	public void testMarketDetailsStringWithJson() {
		KangaExchangeClient client = new KangaExchangeClient();
		String response = client.marketDetailsStringWithJson("ETH-BTC");
		log.debug("Response: {}", response);
		assertTrue("No ping", response.contains("payingCurrency"));
	}

	@Test
	public void testMarketDetailsStringWithModel() {
		KangaExchangeClient client = new KangaExchangeClient();
		String response = client.marketDetailsStringWithModel("ETH-BTC");
		log.debug("Response: {}", response);
		assertTrue("No ping", response.contains("payingCurrency"));
	}


	@Test
	public void testRatesString() {
		KangaExchangeClient client = new KangaExchangeClient();
		String result = client.ratesString();
		log.debug("Result: {}", result);
		assertTrue("No rates", result.contains("fromCurrency"));
		assertTrue("No rates", result.contains("rate"));
	}

	@Test
	public void testRatesJson() {
		KangaExchangeClient client = new KangaExchangeClient();
		JsonObject result = client.ratesJson();
		assertNotNull("No list", result.getJsonArray("list"));
		assertTrue("To small list", result.getJsonArray("list").size() > 20);
	}

}