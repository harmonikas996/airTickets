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

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.service.rentacar.CarRatingService;
import airtickets.service.rentacar.RentACarRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/rentacarratings")
public class RentACarRatingController {
	
	@Autowired
	RentACarRatingService rentACarRatingService;
	
	@GetMapping("/all")
	public List<RentACarRatingDTO> getAllRentACarRatings() {
		return rentACarRatingService.getRentACarRatings();
	}
	
	@GetMapping("/rentacar/{rentACarId}")
	public RentACarRatingDTO getRatingByRentACarId(@PathVariable Long rentACarId){
		return rentACarRatingService.getRatingByRentACarId(rentACarId);
	}
	
	@GetMapping("/all/{rentACarId}")
	public double getRating(@PathVariable Long rentACarId){
		return rentACarRatingService.getRating(rentACarId);
	}

	@GetMapping("/{id}")
	public RentACarRatingDTO getById(@PathVariable Long id) {
		return rentACarRatingService.getRentACarRating(id);
	}

	@PostMapping("/new")
    public RentACarRatingDTO addRentACarRating(@RequestBody RentACarRatingDTO rentACarRatingDTO) {
			return rentACarRatingService.addRentACarRating(rentACarRatingDTO);
	}

	@PutMapping("{id}/update")
	public RentACarRatingDTO updateRentACarRating(@RequestBody RentACarRatingDTO rentACarRatingDTO, @PathVariable Long id) {
		rentACarRatingDTO.setId(id);
		return rentACarRatingService.addRentACarRating(rentACarRatingDTO);
	}

	@DeleteMapping("{id}/delete")
	public void deleteRentACarRating(@PathVariable Long id) {
		rentACarRatingService.deleteRentACarRating(id);
	}
	
}
