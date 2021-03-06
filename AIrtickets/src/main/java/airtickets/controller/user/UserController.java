package airtickets.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.aircompany.FlightReservationDTO;
import airtickets.dto.user.UserDTO;
import airtickets.model.aircompany.FlightReservation;
import airtickets.service.aircompany.FlightReservationService;
import airtickets.service.mail.MailerService;
import airtickets.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailerService mailerService;

	@Autowired
	private FlightReservationService flightReservationService;
	
	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@RequestMapping(method = GET, value = "/user/{userId}")
	//@PreAuthorize("hasRole('ADMIN')")
	public UserDTO loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@RequestMapping(method = GET, value = "/user/all")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> loadAll() {
		return this.userService.findAll();
	}
	
	@RequestMapping(method = GET, value = "/user/clients")
	public List<UserDTO> loadAllClients() {
		return this.userService.findClients(); // TODO srediti da vraca samo klijente
	}

	@RequestMapping("user/profile/{username}")
	//@PreAuthorize("hasRole('USER')")
	public UserDTO userProfile(@PathVariable String username) {
		return this.userService.findByUsername(username);
	}
	
	@RequestMapping(method = PUT, value="user/profile/update/{username}")
	public UserDTO updateUser(@RequestBody UserDTO user, @PathVariable String username) {
		UserDTO u = this.userService.findByUsername(username);
		log.info(user.getEmail());
		user.setEmail(username);
		return this.userService.addUser(user);
	}
	
	@RequestMapping(method = PUT, value="user/authority")
	public UserDTO addUserAuthority(@RequestBody UserDTO user) {
		return this.userService.addAuthority(user);
	}
	
	@RequestMapping(method = GET, value = "/user/search/{name1}")
	public List<UserDTO> searchUsers(@PathVariable String name1) {
		return userService.searchUsers(name1);
	}
	
	@RequestMapping(method = GET, value = "finishReservation/{email}/{flightReservationId}")
	public void  finishReservation(@PathVariable String email, @PathVariable long flightReservationId) {
		FlightReservation fr = flightReservationService.getFlightReservationObject(flightReservationId);
		mailerService.sendReservationDetails(fr, email);
	}
	
}
