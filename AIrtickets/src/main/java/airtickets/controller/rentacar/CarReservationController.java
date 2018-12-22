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

import airtickets.dto.rentacar.CarReservationDTO;
import airtickets.service.rentacar.CarReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carreservations")
public class CarReservationController {
	
	@Autowired
	private CarReservationService carReservationService;

	@GetMapping("/all")
	public List<CarReservationDTO> getAllCarReservations() {
		return carReservationService.getCarReservations();
	}

	@GetMapping("/{id}")
	public CarReservationDTO getById(@PathVariable Long id) {
		return carReservationService.getCarReservation(id);
	}

	@PostMapping("/new")
    public CarReservationDTO addcarReservation(@RequestBody CarReservationDTO carReservation) {
			return carReservationService.addCarReservation(carReservation);
	}

	@PutMapping("{id}/update")
	public CarReservationDTO updateCarReservation(@RequestBody CarReservationDTO carReservation, @PathVariable Long id) {
		carReservation.setId(id);
		return carReservationService.addCarReservation(carReservation);
	}

	@DeleteMapping("{id}/delete")
	public void deleteCarReservation(@PathVariable Long id) {
		carReservationService.deleteCarReservation(id);
	}
	
}
