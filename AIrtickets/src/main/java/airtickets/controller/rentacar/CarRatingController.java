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

import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.service.rentacar.CarRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/carratings")
public class CarRatingController {
	
	@Autowired
	CarRatingService carRatingService;
	
	@GetMapping("/all")
	public List<CarRatingDTO> getAllCarRatings() {
		return carRatingService.getCarRatings();
	}

	@GetMapping("/{id}")
	public CarRatingDTO getById(@PathVariable Long id) {
		return carRatingService.getCarRating(id);
	}

	@PostMapping("/new")
    public CarRatingDTO addCarRating(@RequestBody CarRatingDTO carRatingDTO) {
			return carRatingService.addCarRating(carRatingDTO);
	}

	@PutMapping("{id}/update")
	public CarRatingDTO updateCarRating(@RequestBody CarRatingDTO carRatingDTO, @PathVariable Long id) {
		carRatingDTO.setId(id);
		return carRatingService.addCarRating(carRatingDTO);
	}

	@DeleteMapping("{id}/delete")
	public void deleteCarRating(@PathVariable Long id) {
		carRatingService.deleteCarRating(id);
	}
	
}
