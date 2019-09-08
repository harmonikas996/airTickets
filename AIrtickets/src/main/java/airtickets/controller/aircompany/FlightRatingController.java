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

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.service.aircompany.FlightRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/flightratings")
public class FlightRatingController {
	
	@Autowired
	private FlightRatingService flightRatingService;
	
	@GetMapping("/all")
	public List<FlightRatingDTO> getAllFlightRatings(){
		return flightRatingService.getFlightRatings();
	}
	
	@GetMapping("/flight/{flightId}")
	public FlightRatingDTO getRatingByFlightId(@PathVariable Long flightId){
		return flightRatingService.getRatingByFlightId(flightId);
	}
	
	@GetMapping("/{id}")
	public FlightRatingDTO getById(@PathVariable Long id){
		return flightRatingService.getFlightRating(id);
	}
	
	@PostMapping("/new")
	public FlightRatingDTO addFlightRating(@RequestBody FlightRatingDTO flightRating) {
		return flightRatingService.addFlightRating(flightRating);
	}
	
	@PutMapping("{id}/update")
	public FlightRatingDTO updateFlight(@RequestBody FlightRatingDTO flightRating, @PathVariable Long id) {
		flightRating.setId(id);
		return flightRatingService.addFlightRating(flightRating);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteFlight(@PathVariable Long id) {
		flightRatingService.deleteFlight(id);
	}
}
