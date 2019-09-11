package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.HotelReservationDTO;
import airtickets.model.hotel.HotelReservation;
import airtickets.repo.hotel.HotelReservationRepository;

@Service
public class HotelReservationService {

	@Autowired
	HotelReservationRepository hotelReservationRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<HotelReservationDTO> getHotelReservations(){
		List<HotelReservationDTO> hotelReservationsDTO = new ArrayList<HotelReservationDTO>();
		List<HotelReservation> hotelReservations = hotelReservationRepository.findAll();
		
		for(HotelReservation h : hotelReservations) {
			HotelReservationDTO hotelReservationDTO = new HotelReservationDTO(h);
			hotelReservationsDTO.add(hotelReservationDTO);
		}
		return hotelReservationsDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public HotelReservationDTO getHotelReservation(long id) {
		HotelReservation h = hotelReservationRepository.findById(id);
		HotelReservationDTO hotelReservationDTO = new HotelReservationDTO(h);
		
		return hotelReservationDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public HotelReservationDTO addHotelReservation(HotelReservationDTO hotelReservationDTO) {
		HotelReservation hotelReservation = new HotelReservation(hotelReservationDTO);
		hotelReservationRepository.save(hotelReservation);
		hotelReservationDTO.setId(hotelReservation.getId());
		
		return hotelReservationDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteHotelReservation(long id) {
		hotelReservationRepository.deleteById(id);
	}
}
