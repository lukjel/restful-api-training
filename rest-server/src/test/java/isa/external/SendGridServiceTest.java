package isa.external;

import static org.junit.Assert.*;

public class SendGridServiceTest {

	@org.junit.Test
	public void sendMail() {
		SendGridService srv = new SendGridService();
		srv.sendMail("donald.trump@whitehous.org", "lukasz.z@icloud.com", "Help needed!", "Just to check email is working!");
	}
}