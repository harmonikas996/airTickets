package airtickets.controller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airtickets.mail.MailerHelper;
import airtickets.service.mail.MailerService;

@RestController
public class MailController {
  private static final Logger logger = LoggerFactory.getLogger(MailController.class);

  @Autowired
  private MailerService mailerService;

  @Autowired
  private MailerHelper mailerHelper;

  @RequestMapping("send-mail")
  public String sendMail(@RequestParam(value="address") String address, @RequestParam(value="jwt") String jwt) {
    Long start = System.currentTimeMillis();
    logger.info("starting controller");
    mailerHelper.sendMail(address, jwt);
    return String.format("Message sent to %s in %d ms", address, System.currentTimeMillis() - start);
  }

//  @RequestMapping("send-mail-async")
//  public String sendMailAsync(@RequestParam(value="address") String address) {
//    Long start = System.currentTimeMillis();
//    logger.info("starting controller");
//    mailerService.sendMail(address, jwt);
//    return String.format("Message sent to %s in %d ms", address, System.currentTimeMillis() - start);
//  }
}
