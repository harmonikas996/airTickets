package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.AircompanyRatingDTO;
import airtickets.dto.hotel.HotelRatingDTO;
import airtickets.dto.hotel.RoomRatingDTO;
import airtickets.model.aircompany.AircompanyRating;
import airtickets.model.hotel.HotelRating;
import airtickets.model.hotel.RoomRating;
import airtickets.repo.hotel.RoomRatingRepository;

@Service
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
	
	public double getRating(Long companyId) {
		List<RoomRatingDTO> rooms = new ArrayList<RoomRatingDTO>();
		
		double sum = 0;
		
		for (RoomRating c  : roomRatingRepository.findAllById(companyId)) {
			sum += c.getRating();
			RoomRatingDTO roomRating = new RoomRatingDTO(c);
			rooms.add(roomRating);
 		}
		
		return sum / (rooms.size() * 1.0);
	}
	
	public RoomRatingDTO getRatingByRoomId(long roomId) {
		RoomRating r = roomRatingRepository.findByroomId(roomId);
		
		if( r == null) {
			return new RoomRatingDTO();
		}
		
		RoomRatingDTO room = new RoomRatingDTO(r);
		
		return room;
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
