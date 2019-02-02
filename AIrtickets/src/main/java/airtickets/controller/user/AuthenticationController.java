package airtickets.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airtickets.common.DeviceProvider;
import airtickets.dto.user.UserDTO;
import airtickets.model.user.User;
import airtickets.model.user.UserTokenState;
import airtickets.security.TokenUtils;
import airtickets.security.auth.JwtAuthenticationRequest;
import airtickets.service.mail.MailerService;
import airtickets.service.user.CustomUserDetailsService;
import airtickets.service.user.UserService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private DeviceProvider deviceProvider;
	
	@Autowired
	private MailerService mailerService;
	
	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response/*, Device device*/) throws AuthenticationException, IOException {
		
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername()/*, device*/);
		int expiresIn = tokenUtils.getExpiredIn();
		
		// Vrati token kao odgovor na uspesno autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user.getEmail(), user.getAuthorities()));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    User user = (User) this.userDetailsService.loadUserByUsername(username);

		//Device device = deviceProvider.getCurrentDevice(request);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, user.getEmail(), user.getAuthorities()));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('client')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody User user, HttpServletResponse response) {
		String message = userDetailsService.register(user);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", message);
		
		if(message.equals("Email already exists."))
			return ResponseEntity.badRequest().body(result);
		else {
			String jwt = tokenUtils.generateToken(user.getUsername()/*, device*/);
			mailerService.sendMail(user.getEmail(), jwt);
			return ResponseEntity.accepted().body(result);
		}
	}
	
	@GetMapping("/verify")
	public String verify(@RequestParam(value="username") String username, @RequestParam(value="token") String token){
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if(tokenUtils.validateToken(token, userDetails)) {
			//UserDTO userDTO = userService.findByUsername(userDetails.getUsername());
			//User user = new User(userDTO);
			//userService.addUser(userDTO);
		}
		
		return "Success";
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	
}