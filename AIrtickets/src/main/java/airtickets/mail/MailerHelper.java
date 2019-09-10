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

    SendGrid sendgrid = new SendGrid("");
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
  
//  public void sendReservationDetails(long flightReservationId) {
//  	SimpleMailMessage mailMessage = new SimpleMailMessage();
//      mailMessage.setTo(receiver.getEmail());
//      mailMessage.setSubject("Reservation info!");
//      mailMessage.setFrom("bice10izise@gmail.com");
//      String text = "You have confirmed the following reservation:\n\n\n" +
//      "----------Ticket----------\n" +
//      "Airline: " + reservation.getTicket().getSeats().get(0).getFlight().getAirline().getName() + "\n" +
//      "From: " + reservation.getTicket().getSeats().get(0).getFlight().getStartDestination().getName() + "\n" + 
//      "To: " + reservation.getTicket().getSeats().get(0).getFlight().getFinishDestination().getName() + "\n" +
//      "Departue Time: " + reservation.getTicket().getSeats().get(0).getFlight().getDepartureTime() + "\n" +
//      "Arrival Time: " + reservation.getTicket().getSeats().get(0).getFlight().getArrivalTime() + "\n" + 
//      "Price(euro): " + reservation.getTicket().getPrice() + "\n\n";
//      
//      if(reservation.getVehicleReservation() != null) {
//      	text += "----------Vehicle----------\n" + 
//      			"Rent A Car: " + reservation.getVehicleReservation().getVehicle().getBranchOffice().getRentACar().getName() + "\n" +
//      			"Begin Date: " + reservation.getVehicleReservation().getBeginDate() + "\n" +
//      			"End Date: " + reservation.getVehicleReservation().getEndDate() + "\n" +
//      			"Brand: " + reservation.getVehicleReservation().getVehicle().getBrand() + "\n" +
//      			"Model: " + reservation.getVehicleReservation().getVehicle().getModel() + "\n" +
//      			"Type: " + reservation.getVehicleReservation().getVehicle().getType() + "\n" +
//      			"Number Of Seats: " + reservation.getVehicleReservation().getVehicle().getSeatsNumber() + "\n" +
//      			"Price(euro): " + reservation.getVehicleReservation().getPrice() + "\n\n";
//      }
//      
//      if(reservation.getRoomReservation() != null){
//      	text += "----------Room----------\n" + 
//      			"Hotel: " + reservation.getRoomReservation().getRoom().getFloor().getHotel().getName() + "\n" +
//      			"Begin Date: " + reservation.getRoomReservation().getBeginDate() + "\n" +
//      			"End Date: " + reservation.getRoomReservation().getEndDate() + "\n" +
//      			"Room Type: " + reservation.getRoomReservation().getRoom().getRoomType().getName() + "\n" +
//      			"Number Of Beds: " + reservation.getRoomReservation().getRoom().getNumberOfBeds() + "\n" +
//      			"Price(euro): " + reservation.getRoomReservation().getPrice() + "\n\n";
//      }
//      
//      text+= "-------------------------\n" +
//      		"Price(euro): " + reservation.getPrice() + "\n";
//     
//      mailMessage.setText(text);
//      this.sendEmail(mailMessage);
//  }
}
