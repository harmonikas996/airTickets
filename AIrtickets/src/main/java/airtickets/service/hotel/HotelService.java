package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.HotelDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.rentacar.RentACar;
import airtickets.model.user.User;
import airtickets.repo.hotel.HotelRepository;
import airtickets.repo.user.UserRepository;

@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	UserRepository userRepository;
	
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

	public HotelDTO getHotelByAdmin(String adminUsername) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());
		Hotel h  = hotelRepository.findById(user.getCompany().getId());
		HotelDTO hotel = new HotelDTO(h);
		
		return hotel;
	}
}
