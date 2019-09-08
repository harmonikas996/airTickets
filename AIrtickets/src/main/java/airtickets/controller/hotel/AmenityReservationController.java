package airtickets.controller.hotel;

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

import airtickets.dto.hotel.AmenityDTO;
import airtickets.dto.hotel.AmenityReservationDTO;
import airtickets.service.hotel.AmenityReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/amenityReservations")
public class AmenityReservationController {

	@Autowired
	private AmenityReservationService amenityReservationService;
	
	@GetMapping("/all")
	public List<AmenityReservationDTO> getAllAmenityReservations(){
		return amenityReservationService.getAmenityReservations();
	}
	
	@GetMapping("/{id}")
	public AmenityReservationDTO getById(@PathVariable Long id){
		return amenityReservationService.getAmenityReservation(id);
	}
	
	@PostMapping("/new")
	public AmenityReservationDTO addAmenityReservation(@RequestBody AmenityReservationDTO amenityReservation) {
		return amenityReservationService.addAmenityReservation(amenityReservation);
	}
	
	@PutMapping("{id}/update")
	public AmenityReservationDTO updateAmenity(@RequestBody AmenityReservationDTO amenityReservation, @PathVariable Long id) {
		amenityReservation.setId(id);
		return amenityReservationService.addAmenityReservation(amenityReservation);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteAmenity(@PathVariable Long id) {
		amenityReservationService.deleteAmenityReservation(id);
	}
	
	@PutMapping("makeReservation/{hotelReservationId}")
	public boolean makeReservation(@RequestBody List<AmenityDTO> amenities, @PathVariable Long hotelReservationId) {
		return amenityReservationService.makeReservation(amenities, hotelReservationId);
	}
}
