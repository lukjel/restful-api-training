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
	private static final String API_KEY = "SG.ZRKeHR5fT8WOt2WqTtnOvw.pZ1UqBvi0OizXOCaRMwIdWuXiGWkZoM";

	Logger log = LoggerFactory.getLogger(getClass());

	public void sendMail(String fromEmail, String toEmail, String subject, String body) {
		// TODO
	}

}
