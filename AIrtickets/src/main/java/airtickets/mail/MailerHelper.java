package airtickets.mail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import airtickets.security.TokenUtils;



@Component
public class MailerHelper {
  private static final Logger logger = LoggerFactory.getLogger(MailerHelper.class);
  
  @Autowired
  private TokenUtils tokenUtils;

  public void sendMail(String address, String username) {
	  
    String jwt = tokenUtils.generateToken(username);
	  
	String subject = "Welcome to airTickets! Confirm your email";
	String message = "Click on the link to verify your email address: " + "http://localhost:8080/auth/verify?token="+jwt+"&username="+username;
    Email from = new Email("sandor.taher@plutocow.com");
    Email to = new Email(address);
    Content emailContent = new Content("text/plain", message);

    // Umesto AA u tokenu stavi SG
    SendGrid sendgrid = new SendGrid("AA.HdFbLBElS2WzFUZfbut3hw.ZyBn59_1vWbTch8ylWi6o4Yq5qSW_A454OSXLBRQXyw");
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
