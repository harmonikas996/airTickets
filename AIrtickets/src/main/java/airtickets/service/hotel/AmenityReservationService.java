package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.AmenityReservationDTO;
import airtickets.model.hotel.AmenityReservation;
import airtickets.repo.hotel.AmenityReservationRepository;

@Service
public class AmenityReservationService {
	
	@Autowired
	AmenityReservationRepository amenityReservationRepository;
	
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
}
