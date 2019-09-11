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

import airtickets.model.aircompany.FlightReservation;
import airtickets.model.hotel.HotelReservation;
import airtickets.model.hotel.RoomReservation;
import airtickets.model.rentacar.CarReservation;
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
    Content emailContent = new Content("text/html", message);

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
  
  public void sendReservationDetails(FlightReservation fr, String email) {
	 
    CarReservation cr = fr.getCarReservation();
    HotelReservation hr = fr.getHotelReservation();
	  
  	String subject = "[Airtickets] Reservation Confirmation";
	String message = "<h1>You've booked your next flight on Airtickets</h1><br><br>" +
		      "<h2>Flight Reservation</h2><br>" +
		      "<p style=\"font-weight:100\">From: <b>" + fr.getFlight().getPlaceFrom().getCity() + "</b></p>" + 
		      "<p style=\"font-weight:100\">To: <b>" + fr.getFlight().getPlaceTo().getCity() + "</b></p>" +
		      "<p style=\"font-weight:100\">Departue Time: <b>" + fr.getFlight().getTimeBegin() + "</b></p>" +
		      "<p style=\"font-weight:100\">Arrival Time: <b>" + fr.getFlight().getTimeBegin() + "</b></p>" + 
		      "<p style=\"font-weight:100\">Aircompany: <b>" + fr.getFlight().getCompany().getName() + "</b></p>" +
		      "<p style=\"font-weight:100\">Price: <b>" + fr.getFlight().getPrice() + "€</b></p>" +
		      "<hr>";
		      
		      if(cr != null) {
		      	message += "<br><h2>Car Reservation</h2><br>" + 
		      			"<p style=\"font-weight:100\">Rent A Car: <b>" + cr.getVehicle().getRentACar().getName() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Begin Date: <b>" + cr.getDateFrom() + "</b></p>" +
		      			"<p style=\"font-weight:100\">End Date: <b>" + cr.getDateTo() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Brand: <b>" + cr.getVehicle().getBrand() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Model: <b>" + cr.getVehicle().getModel() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Type: <b>" + cr.getVehicle().getTypeString() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Number Of Seats: <b>" + cr.getVehicle().getNumberOfSeats() + "</b></p>" +
		      			"<p style=\"font-weight:100\">Price: <b>" + cr.getVehicle().getPricePerDay() + "€/day</b></p>" +
		      			"<hr>";
		      }
		      
		      if(hr != null){
		    	  message += "<br><h2>Room Reservation</h2><br>" +
		    			     "<p style=\"font-weight:100\">Hotel: " + hr.getHotel().getName() + "</b></p>" +
	    				     "<p style=\"font-weight:100\">Begin Date: " + hr.getDateFrom() + "</b></p>" +
	    				     "<p style=\"font-weight:100\">End Date: " + hr.getDateTo() + "</b></p>";
		    	  message += "<br><h4>Room(s)</h4>"; 
		    	  for (RoomReservation roomReservation : hr.getRoomReservations()) {
					
	    		   message += "<p style=\"font-weight:100\">Room Type: " + roomReservation.getRoom().getType() + "</b></p>" +
	    				      "<p style=\"font-weight:100\">Room Number: " + roomReservation.getRoom().getNumber() + "</b></p>" +
	    				      "<p style=\"font-weight:100\">Room Floor: " + roomReservation.getRoom().getFloor() + "</b></p>" +
		    				  "<p style=\"font-weight:100\">Number Of Beds: " + roomReservation.getRoom().getNoOfBeds() + "</b></p>" +
		    				  "<hr>";
		    	  }
		    	  message+= "<br><h4>Price(euro): " + hr.getPrice() + "<h4>";
		      }
		      
	Email from = new Email("bepar@mailfile.net");
	Email to = new Email(email);
	Content emailContent = new Content("text/html", message);
	
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
}
