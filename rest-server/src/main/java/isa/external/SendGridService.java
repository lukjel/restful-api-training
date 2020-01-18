package isa.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import static isa.hibernate.util.JsonHelper.toArray;
import static isa.hibernate.util.JsonHelper.toJson;

/**
 * curl --request POST \
 * --url https://api.sendgrid.com/v3/mail/send \
 * --header "Authorization: Bearer $SENDGRID_API_KEY" \
 * --header 'Content-Type: application/json' \
 * --data '{"personalizations": [{"to": [{"email": "test@example.com"}]}],"from": {"email": "test@example.com"},"subject": "Sending with SendGrid is Fun","content": [{"type": "text/plain", "value": "and easy to do anywhere, even with cURL"}]}'
 */

@Stateless
public class SendGridService {

	private static final String BASE_URL = "https://api.sendgrid.com/v3";
	private static final String API_KEY = "";

	Logger log = LoggerFactory.getLogger(getClass());

	Client client = ClientBuilder.newClient();
	WebTarget sendGridTarget = client.target(BASE_URL);

	public void sendMail(String fromEmail, String toEmail, String subject, String body) {
		Invocation.Builder request = sendGridTarget
			.path("mail")
			.path("send").request();
		JsonObject json = toJson(
			"personalizations", toArray(toJson("to", toArray(toJson("email", toEmail)))),
			"from", toJson("email", fromEmail),
			"subject", subject,
			"content", toArray(toJson(
				"type", "text/plain",
				"value", body
			)));
		log.debug("JSON:{}", json.toString());
		try (Response response = request
			.header("Authorization", "Bearer " + API_KEY)
			.post(Entity.json(json))) {
			if (Response.Status.ACCEPTED.equals(response.getStatusInfo())) {
				log.debug("Mail sent.");
				return;
			}
			log.debug("Response: {} :: {}", response.getStatus(), response.readEntity(String.class));
			int status = response.getStatus();
			throw new IllegalStateException("Error: " + status);
		}
	}

}
