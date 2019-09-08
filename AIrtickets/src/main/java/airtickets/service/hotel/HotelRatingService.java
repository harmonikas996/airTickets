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
	
	public HotelRatingDTO getRatingByHotelId(long hotelId) {
		HotelRating h = hotelRatingRepository.findByhotelId(hotelId);
		
		if( h == null) {
			return new HotelRatingDTO();
		}
		
		HotelRatingDTO hotel = new HotelRatingDTO(h);
		
		return hotel;
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

	public double getRating(Long companyId) {
		List<HotelRatingDTO> hotels = new ArrayList<HotelRatingDTO>();
		
		double sum = 0;
		
		for (HotelRating c  : hotelRatingRepository.findAllById(companyId)) {
			sum += c.getRating();
			HotelRatingDTO hotelRating = new HotelRatingDTO(c);
			hotels.add(hotelRating);
 		}
		
		return sum / (hotels.size() * 1.0);
	}
}
