package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.HotelReservationDTO;
import airtickets.model.hotel.HotelReservation;
import airtickets.repo.hotel.HotelReservationRepository;

public class HotelReservationService {

	@Autowired
	HotelReservationRepository hotelReservationRepository;
	
	public List<HotelReservationDTO> getHotelReservations(){
		List<HotelReservationDTO> hotelReservationsDTO = new ArrayList<HotelReservationDTO>();
		List<HotelReservation> hotelReservations = hotelReservationRepository.findAll();
		
		for(HotelReservation h : hotelReservations) {
			HotelReservationDTO hotelReservationDTO = new HotelReservationDTO(h);
			hotelReservationsDTO.add(hotelReservationDTO);
		}
		return hotelReservationsDTO;
	}
	
	public HotelReservationDTO getHotelReservation(long id) {
		HotelReservation h = hotelReservationRepository.findById(id);
		HotelReservationDTO hotelReservationDTO = new HotelReservationDTO(h);
		
		return hotelReservationDTO;
	}
	
	public HotelReservationDTO addHotelReservation(HotelReservationDTO hotelReservationDTO) {
		HotelReservation hotelReservation = new HotelReservation(hotelReservationDTO);
		hotelReservationRepository.save(hotelReservation);
		hotelReservationDTO.setId(hotelReservation.getId());
		
		return hotelReservationDTO;
	}
	
	public void deleteHotelReservation(long id) {
		hotelReservationRepository.deleteById(id);
	}
}
