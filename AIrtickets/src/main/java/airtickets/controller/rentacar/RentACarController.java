package airtickets.controller.rentacar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import airtickets.service.rentacar.RentACarService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rentacars")
public class RentACarController {

	@Autowired
	private RentACarService rentACarService;

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
}
