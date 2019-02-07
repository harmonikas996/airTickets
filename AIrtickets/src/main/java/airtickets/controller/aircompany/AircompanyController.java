package airtickets.controller.aircompany;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.user.UserDTO;
import airtickets.service.aircompany.AircompanyService;
import airtickets.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/aircompanies")
public class AircompanyController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AircompanyService aircompanyService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<AircompanyDTO> getAllAircompanies(){
		return aircompanyService.getAircompanies();
	}
	
	@GetMapping("/{id}")
	public AircompanyDTO getById(@PathVariable Long id){
		return aircompanyService.getAircompany(id);
	}
	
	@PostMapping("/new")
	public AircompanyDTO addAircompany(@RequestBody AircompanyDTO aircompany) {
		return aircompanyService.addAircompany(aircompany);
	}
	
	@PutMapping("{id}/update")
	public AircompanyDTO updateAircompany(@RequestBody AircompanyDTO aircompany, @PathVariable Long id) {
		aircompany.setId(id);
		return aircompanyService.addAircompany(aircompany);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteAircompany(@PathVariable Long id) {
		aircompanyService.deleteAircompany(id);
	}
	
	@GetMapping("/admin/{username}")
	@PreAuthorize("hasAuthority('aircompany')")
	public AircompanyDTO getByAdminUsername(@PathVariable String username) {
		//log.info("u metodi GETBYADMINUSERNAME");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		UserDTO userDTO = userService.findByUsername(userDetails.getUsername());
		
		return aircompanyService.getAircompany(userDTO.getCompany());
	}
	
}
