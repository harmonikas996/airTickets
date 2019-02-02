package airtickets.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.user.UserDTO;
import airtickets.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

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

	@RequestMapping("user/profile/{username}")
	//@PreAuthorize("hasRole('USER')")
	public UserDTO userProfile(@PathVariable String username) {
		return this.userService.findByUsername(username);
	}
	
	@RequestMapping(method = PUT, value="user/profile/update/{username}")
	public UserDTO updateUser(@RequestBody UserDTO user, @PathVariable String username) {
		user.setEmail(username);
		return this.userService.addUser(user);
	}
	
}
