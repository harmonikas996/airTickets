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
import airtickets.service.hotel.AmenityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/amenities")
public class AmenityController {

	@Autowired
	private AmenityService amenityService;
	
	@GetMapping("/all")
	public List<AmenityDTO> getAllAmenities(){
		return amenityService.getAmenities();
	}
	
	@GetMapping("/{id}")
	public AmenityDTO getById(@PathVariable Long id){
		return amenityService.getAmenity(id);
	}
	
	@PostMapping("/new")
	public AmenityDTO addAmenity(@RequestBody AmenityDTO amenity) {
		return amenityService.addAmenity(amenity);
	}
	
	@PutMapping("{id}/update")
	public AmenityDTO updateAmenity(@RequestBody AmenityDTO amenity, @PathVariable Long id) {
		amenity.setId(id);
		return amenityService.addAmenity(amenity);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteAmenity(@PathVariable Long id) {
		amenityService.deleteAmenity(id);
	}
}
