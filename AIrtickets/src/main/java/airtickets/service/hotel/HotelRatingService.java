package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.HotelRatingDTO;
import airtickets.model.hotel.HotelRating;
import airtickets.repo.hotel.HotelRatingRepository;

@Service
public class HotelRatingService {

	@Autowired
	HotelRatingRepository hotelRatingRepository;
	
	public List<HotelRatingDTO> getHotelRatings(){
		List<HotelRatingDTO> hotelRatingsDTO = new ArrayList<HotelRatingDTO>();
		List<HotelRating> hotelRatings = hotelRatingRepository.findAll();
		
		for(HotelRating h : hotelRatings) {
			HotelRatingDTO hotelRatingDTO = new HotelRatingDTO(h);
			hotelRatingsDTO.add(hotelRatingDTO);
		}
		return hotelRatingsDTO;
	}
	
	public HotelRatingDTO getHotelRating(long id) {
		HotelRating h = hotelRatingRepository.findById(id);
		HotelRatingDTO hotelRatingDTO = new HotelRatingDTO(h);
		
		return hotelRatingDTO;
	}
	
	public HotelRatingDTO addHotelRating(HotelRatingDTO hotelRatingDTO) {
		HotelRating hotel = new HotelRating(hotelRatingDTO);
		hotelRatingRepository.save(hotel);
		hotelRatingDTO.setId(hotel.getId());
		
		return hotelRatingDTO;
	}
	
	public void deleteHotelRating(long id) {
		hotelRatingRepository.deleteById(id);
	}
}
