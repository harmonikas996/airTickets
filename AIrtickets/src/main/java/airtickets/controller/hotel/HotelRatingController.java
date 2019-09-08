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

import airtickets.dto.hotel.HotelDTO;
import airtickets.dto.hotel.HotelRatingDTO;
import airtickets.service.hotel.HotelRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/hotelRatings")
public class HotelRatingController {

	@Autowired
	private HotelRatingService hotelRatingService;
	
	@GetMapping("/all")
	public List<HotelRatingDTO> getAllHotelRatings(){
		return hotelRatingService.getHotelRatings();
	}
	
	@GetMapping("/{id}")
	public HotelRatingDTO getById(@PathVariable Long id){
		return hotelRatingService.getHotelRating(id);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public HotelRatingDTO getRatingByHotelId(@PathVariable Long hotelId){
		return hotelRatingService.getRatingByHotelId(hotelId);
	}
	
	@GetMapping("/all/{companyId}")
	public double getRating(@PathVariable Long companyId){
		return hotelRatingService.getRating(companyId);
	}

	@PostMapping("/new")
	public HotelRatingDTO addHotelRating(@RequestBody HotelRatingDTO hotelRating) {
		return hotelRatingService.addHotelRating(hotelRating);
	}
	
	@PutMapping("{id}/update")
	public HotelRatingDTO updateHotelRating(@RequestBody HotelRatingDTO hotelRating, @PathVariable Long id) {
		hotelRating.setId(id);
		return hotelRatingService.addHotelRating(hotelRating);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteHotelRating(@PathVariable Long id) {
		hotelRatingService.deleteHotelRating(id);
	}
}
