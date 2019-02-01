package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.HotelDTO;
import airtickets.model.hotel.Hotel;
import airtickets.repo.hotel.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public List<HotelDTO> getHotels(){
		List<HotelDTO> hotelsDTO = new ArrayList<HotelDTO>();
		List<Hotel> hotels = hotelRepository.findAll();
		
		for(Hotel h : hotels) {
			HotelDTO hotelDTO = new HotelDTO(h);
			hotelsDTO.add(hotelDTO);
		}
		return hotelsDTO;
	}
	
	public HotelDTO getHotel(long id) {
		Hotel h = hotelRepository.findById(id);
		HotelDTO hotelDTO = new HotelDTO(h);
		
		return hotelDTO;
	}
	
	public HotelDTO addHotel(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel(hotelDTO);
		hotelRepository.save(hotel);
		hotelDTO.setId(hotel.getId());
		
		return hotelDTO;
	}
	
	public void deleteHotel(long id) {
		hotelRepository.deleteById(id);
	}
}
