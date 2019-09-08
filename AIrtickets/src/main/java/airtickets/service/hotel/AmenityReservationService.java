package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.AmenityDTO;
import airtickets.dto.hotel.AmenityReservationDTO;
import airtickets.model.hotel.Amenity;
import airtickets.model.hotel.AmenityReservation;
import airtickets.model.hotel.HotelReservation;
import airtickets.repo.hotel.AmenityReservationRepository;
import airtickets.repo.hotel.HotelReservationRepository;

@Service
public class AmenityReservationService {
	
	@Autowired
	AmenityReservationRepository amenityReservationRepository;
	
	@Autowired
	HotelReservationRepository hotelReservationRepository;
	
	public List<AmenityReservationDTO> getAmenityReservations(){
		List<AmenityReservationDTO> amenityReservationsDTO = new ArrayList<AmenityReservationDTO>();
		List<AmenityReservation> amenityReservations = amenityReservationRepository.findAll();
		
		for(AmenityReservation a : amenityReservations) {
			AmenityReservationDTO amenityReservationDTO = new AmenityReservationDTO(a);
			amenityReservationsDTO.add(amenityReservationDTO);
		}
		return amenityReservationsDTO;
	}
	
	public AmenityReservationDTO getAmenityReservation(long id) {
		AmenityReservation a = amenityReservationRepository.findById(id);
		AmenityReservationDTO amenityReservation = new AmenityReservationDTO(a);
		
		return amenityReservation;
	}
	
	public AmenityReservationDTO addAmenityReservation(AmenityReservationDTO amenityreservationDTO) {
		AmenityReservation amenity = new AmenityReservation(amenityreservationDTO);
		amenityReservationRepository.save(amenity);
		amenityreservationDTO.setId(amenity.getId());
		
		return amenityreservationDTO;
	}
	
	public void deleteAmenityReservation(long id) {
		amenityReservationRepository.deleteById(id);
	}

	public boolean makeReservation(List<AmenityDTO> amenities, Long hotelReservationId) {

		List<AmenityReservation> amenityReservations = amenityReservationRepository.findAll();
		HotelReservation hr = hotelReservationRepository.getOne(hotelReservationId);
		for(AmenityDTO am : amenities) {
			AmenityReservation ar = new AmenityReservation();
			ar.setAmenity(new Amenity(am));
			ar.setHotelReservation(hr);
			amenityReservationRepository.save(ar);
		}
		return true;
	}
}
