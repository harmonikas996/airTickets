package airtickets.controller.aircompany;

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

import airtickets.dto.aircompany.FlightReservationDTO;
import airtickets.service.aircompany.FlightReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/flightreservations")
public class FlightReservationController {
	
	@Autowired
	private FlightReservationService flightReservationService;
	
	@GetMapping("/all")
	public List<FlightReservationDTO> getAllFlightResevations(){
		return flightReservationService.getFlightReservations();
	}
	
	@GetMapping("/user/{id}")
	public List<FlightReservationDTO> getFlightReservationsByUser(@PathVariable Long id){
		return flightReservationService.getFlightReservationsByUser(id);
	}
	
	@GetMapping("/{id}")
	public FlightReservationDTO getById(@PathVariable Long id){
		return flightReservationService.getFlightReservation(id);
	}
	
	@PostMapping("/new")
	public FlightReservationDTO addFlightReservation(@RequestBody FlightReservationDTO flight) {
		return flightReservationService.addFlightReservation(flight);
	}
	
	@PutMapping("{id}/update")
	public FlightReservationDTO updateFlight(@RequestBody FlightReservationDTO flight, @PathVariable Long id) {
		flight.setId(id);
		return flightReservationService.addFlightReservation(flight);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteFlight(@PathVariable Long id) {
		flightReservationService.deleteFlightReservation(id);
	}

}
