package airtickets.mail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sendgrid.*;

@Component
public class MailerHelper {
  private static final Logger logger = LoggerFactory.getLogger(MailerHelper.class);

  public void sendMail(String address, String jwt) {
	String subject = "Welcome to airTickets! Confirm your email";
	String message = "Click on the link to verify your email address: " + "http://localhost:8080/auth/verify?token="+jwt;
    Email from = new Email("gupo@maxmail.info");
    Email to = new Email(address);
    Content emailContent = new Content("text/plain", message);

    SendGrid sendgrid = new SendGrid("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");
    Request request = new Request();

    Mail mail = new Mail(from, subject, to, emailContent);

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendgrid.api(request);
      logger.info("--> Status: " + response.getStatusCode());
      logger.info("--> Body: " + response.getBody());
      logger.info("--> Header: " + response.getHeaders());
    } catch(IOException ex) {
      logger.error(ex.getMessage());
    }
  }
}
