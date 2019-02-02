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

import airtickets.dto.hotel.HotelReservationDTO;
import airtickets.service.hotel.HotelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/hotelReservations")
public class HotelReservationController {

	@Autowired
	private HotelReservationService hotelReservationService;
	
	@GetMapping("/all")
	public List<HotelReservationDTO> getAllHotelReservations(){
		return hotelReservationService.getHotelReservations();
	}
	
	@GetMapping("/{id}")
	public HotelReservationDTO getById(@PathVariable Long id){
		return hotelReservationService.getHotelReservation(id);
	}
	
	@PostMapping("/new")
	public HotelReservationDTO addHotelReservation(@RequestBody HotelReservationDTO hotelReservation) {
		return hotelReservationService.addHotelReservation(hotelReservation);
	}
	
	@PutMapping("{id}/update")
	public HotelReservationDTO updateHotelReservation(@RequestBody HotelReservationDTO hotelReservation, @PathVariable Long id) {
		hotelReservation.setId(id);
		return hotelReservationService.addHotelReservation(hotelReservation);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteHotelReservation(@PathVariable Long id) {
		hotelReservationService.deleteHotelReservation(id);
	}
}
