package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.AmenityDTO;
import airtickets.model.hotel.Amenity;
import airtickets.repo.hotel.AmenityRepository;

@Service
public class AmenityService {

	@Autowired
	AmenityRepository amenityRepository;
	
	public List<AmenityDTO> getAmenities(){
		List<AmenityDTO> amenitiesDTO = new ArrayList<AmenityDTO>();
		List<Amenity> amenities = amenityRepository.findAll();
		
		for(Amenity a : amenities) {
			AmenityDTO amenityDTO = new AmenityDTO(a);
			amenitiesDTO.add(amenityDTO);
		}
		return amenitiesDTO;
	}
	
	public AmenityDTO getAmenity(long id) {
		Amenity a = amenityRepository.findById(id);
		AmenityDTO amenity = new AmenityDTO(a);
		
		return amenity;
	}
	
	public AmenityDTO addAmenity(AmenityDTO amenityDTO) {
		Amenity amenity= new Amenity(amenityDTO);
		amenityRepository.save(amenity);
		amenityDTO.setId(amenity.getId());
		
		return amenityDTO;
	}
	
	public void deleteAmenity(long id) {
		amenityRepository.deleteById(id);
	}
}
