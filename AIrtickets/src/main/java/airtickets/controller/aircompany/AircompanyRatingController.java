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

import airtickets.dto.aircompany.AircompanyRatingDTO;
import airtickets.service.aircompany.AircompanyRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/aircompanyratings")
public class AircompanyRatingController {
	
	@Autowired
	private AircompanyRatingService aircompanyRatingService;
		
	@GetMapping("/all")
	public List<AircompanyRatingDTO> getAllAircompanyRatings(){
		return aircompanyRatingService.getAircompanyRatings();
	}
	
	@GetMapping("/{id}")
	public AircompanyRatingDTO getById(@PathVariable Long id){
		return aircompanyRatingService.getAircompanyRating(id);
	}
	
	@PostMapping("/new")
	public AircompanyRatingDTO addAircompanyRating(@RequestBody AircompanyRatingDTO aircompanyRating) {
		return aircompanyRatingService.addAircompanyRating(aircompanyRating);
	}
	
	@PutMapping("{id}/update")
	public AircompanyRatingDTO updateAircompanyRating(@RequestBody AircompanyRatingDTO aircompanyRating, @PathVariable Long id) {
		aircompanyRating.setId(id);
		return aircompanyRatingService.addAircompanyRating(aircompanyRating);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteAircompanyRating(@PathVariable Long id) {
		aircompanyRatingService.deleteAircompanyRating(id);
	}
}
