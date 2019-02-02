package airtickets.controller.rentacar;

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

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.user.UserDTO;
import airtickets.service.rentacar.RentACarService;
import airtickets.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/rentacars")
public class RentACarController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RentACarService rentACarService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<RentACarDTO> getAllRentACars() {
		return rentACarService.getRentACars();
	}

	@GetMapping("/{id}")
	public RentACarDTO getById(@PathVariable Long id) {
		return rentACarService.getRentACar(id);
	}

	@PostMapping("/new")
    public RentACarDTO addRentACar(@RequestBody RentACarDTO rentACar) {
			return rentACarService.addRentACar(rentACar);
	}

	@PutMapping("{id}/update")
	public RentACarDTO updateRentACar(@RequestBody RentACarDTO rentACar, @PathVariable Long id) {
		rentACar.setId(id);
		return rentACarService.addRentACar(rentACar);
	}

	@DeleteMapping("{id}/delete")
	public void deleteRentACar(@PathVariable Long id) {
		rentACarService.deleteRentACar(id);
	}
	
	@GetMapping("/admin/{username}")
	@PreAuthorize("hasAuthority('rentacar')")
	public RentACarDTO getByAdminUsername(@PathVariable String username) {
		return rentACarService.getRentACarByAdmin(username);
	}
}
