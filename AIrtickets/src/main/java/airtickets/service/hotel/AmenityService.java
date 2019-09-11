package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.AmenityDTO;
import airtickets.model.hotel.Amenity;
import airtickets.repo.hotel.AmenityRepository;

@Service
public class AmenityService {

	@Autowired
	AmenityRepository amenityRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<AmenityDTO> getAmenities(){
		List<AmenityDTO> amenitiesDTO = new ArrayList<AmenityDTO>();
		List<Amenity> amenities = amenityRepository.findAll();
		
		for(Amenity a : amenities) {
			AmenityDTO amenityDTO = new AmenityDTO(a);
			amenitiesDTO.add(amenityDTO);
		}
		return amenitiesDTO;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<AmenityDTO> getAmenitiesByHotel(Long id) {
		List<AmenityDTO> amenitiesDTO = new ArrayList<AmenityDTO>();
		List<Amenity> amenities = amenityRepository.getAmenitiesByHotel(id);
		
		for(Amenity a : amenities) {
			AmenityDTO amenityDTO = new AmenityDTO(a);
			amenitiesDTO.add(amenityDTO);
		}
		return amenitiesDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public AmenityDTO getAmenity(long id) {
		Amenity a = amenityRepository.findById(id);
		AmenityDTO amenity = new AmenityDTO(a);
		
		return amenity;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public AmenityDTO addAmenity(AmenityDTO amenityDTO) {
		Amenity amenity= new Amenity(amenityDTO);
		amenityRepository.save(amenity);
		amenityDTO.setId(amenity.getId());
		
		return amenityDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteAmenity(long id) {
		amenityRepository.deleteById(id);
	}
}
