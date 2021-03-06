package airtickets.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import airtickets.mail.MailerHelper;
import airtickets.model.aircompany.FlightReservation;

@Service
public class MailerService {
  private static final Logger logger = LoggerFactory.getLogger(MailerService.class);

  @Autowired
  private MailerHelper mailerHelper;

  @Async
  public void sendMail(String address, String username) {
    mailerHelper.sendMail(address, username);
  }
  
  @Async
  public void sendReservationDetails(FlightReservation fr, String email) {
    mailerHelper.sendReservationDetails(fr, email);
  }
}
