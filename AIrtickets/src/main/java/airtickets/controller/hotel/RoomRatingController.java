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

import airtickets.dto.hotel.RoomRatingDTO;
import airtickets.service.hotel.RoomRatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/roomRatings")
public class RoomRatingController {

	@Autowired
	private RoomRatingService roomRatingService;
	
	@GetMapping("/all")
	public List<RoomRatingDTO> getAllRoomRatings(){
		return roomRatingService.getRoomRatings();
	}
	
	@GetMapping("/{id}")
	public RoomRatingDTO getById(@PathVariable Long id){
		return roomRatingService.getRoomRating(id);
	}
	
	@PostMapping("/new")
	public RoomRatingDTO addRoomRating(@RequestBody RoomRatingDTO roomRating) {
		return roomRatingService.addRoomRating(roomRating);
	}
	
	@PutMapping("{id}/update")
	public RoomRatingDTO updateRoomRating(@RequestBody RoomRatingDTO roomRating, @PathVariable Long id) {
		roomRating.setId(id);
		return roomRatingService.addRoomRating(roomRating);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteRoomRating(@PathVariable Long id) {
		roomRatingService.deleteRoomRating(id);
	}
}