package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.RoomRatingDTO;
import airtickets.model.hotel.RoomRating;
import airtickets.repo.hotel.RoomRatingRepository;

public class RoomRatingService {
	
	@Autowired
	RoomRatingRepository roomRatingRepository;
	
	public List<RoomRatingDTO> getRoomRatings(){
		List<RoomRatingDTO> roomRatingsDTO = new ArrayList<RoomRatingDTO>();
		List<RoomRating> roomRatings = roomRatingRepository.findAll();
		
		for(RoomRating r : roomRatings) {
			RoomRatingDTO roomRatingDTO = new RoomRatingDTO(r);
			roomRatingsDTO.add(roomRatingDTO);
		}
		return roomRatingsDTO;
	}
	
	public RoomRatingDTO getRoomRating(long id) {
		RoomRating r = roomRatingRepository.findById(id);
		RoomRatingDTO roomRatingDTO = new RoomRatingDTO(r);
		
		return roomRatingDTO;
	}
	
	public RoomRatingDTO addRoomRating(RoomRatingDTO roomRatingDTO) {
		RoomRating roomRating = new RoomRating(roomRatingDTO);
		roomRatingRepository.save(roomRating);
		roomRatingDTO.setId(roomRating.getId());
		
		return roomRatingDTO;
	}
	
	public void deleteRoomRating(long id) {
		roomRatingRepository.deleteById(id);
	}
}
