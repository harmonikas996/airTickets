package airtickets.controller.aircompany;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.aircompany.FlightDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.service.aircompany.FlightService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/flights")
public class FlightController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/all")
	public List<FlightDTO> getAllFlights(){
		return flightService.getFlights();
	}
	
	@GetMapping("/{id}")
	public FlightDTO getById(@PathVariable Long id){
		return flightService.getFlight(id);
	}
	
	@PostMapping("/new")
	public FlightDTO addFlight(@RequestBody FlightDTO flight) {
		log.info(flight.toString());
		return flightService.addFlight(flight);
	}
	
	@PutMapping("{id}/update")
	public FlightDTO updateFlight(@RequestBody FlightDTO flight, @PathVariable Long id) {
		flight.setId(id);
		return flightService.addFlight(flight);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteFlight(@PathVariable Long id) {
		flightService.deleteFlight(id);
	}
	
	@GetMapping("/search")
	public List<FlightDTO> searchFlights(
			@RequestParam(value="placeFromId") String placeFromId,
			@RequestParam(value="placeToId") String placeToId,
			@RequestParam(value="timeBegin") String date
			) {
		
		return flightService.searchFlights(placeFromId, placeToId, date);
	}
	
}
