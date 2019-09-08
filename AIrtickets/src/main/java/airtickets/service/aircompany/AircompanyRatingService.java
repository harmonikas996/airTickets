package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.AircompanyRatingDTO;
import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.model.aircompany.AircompanyRating;
import airtickets.model.aircompany.FlightRating;
import airtickets.model.rentacar.CarRating;
import airtickets.repo.aircompany.AircompanyRatingRepository;

@Service
public class AircompanyRatingService {
	
	@Autowired
	AircompanyRatingRepository aircompanyRatingRepository;
	
	public List<AircompanyRatingDTO> getAircompanyRatings(){
		List<AircompanyRatingDTO> aircompanyRatings = new ArrayList<AircompanyRatingDTO>();
		List<AircompanyRating> air = aircompanyRatingRepository.findAll();
		
		for(AircompanyRating a : air) {
			AircompanyRatingDTO aircompanyRating = new AircompanyRatingDTO(a);
			aircompanyRatings.add(aircompanyRating);
		}
		
		return aircompanyRatings;
	}
	
	public AircompanyRatingDTO getAircompanyRating(long id) {
		AircompanyRating a = aircompanyRatingRepository.findById(id);
		AircompanyRatingDTO aircompanyRating = new AircompanyRatingDTO(a);
		
		return aircompanyRating;
	}
	
	public AircompanyRatingDTO getRatingByAirCompanyId(long companyId) {
		AircompanyRating a = aircompanyRatingRepository.findBycompanyId(companyId);
		
		if( a == null) {
			return new AircompanyRatingDTO();
		}
		
		AircompanyRatingDTO company = new AircompanyRatingDTO(a);
		
		return company;
	}
	
	public AircompanyRatingDTO addAircompanyRating(AircompanyRatingDTO aircompanyRatingDTO) {
		AircompanyRating aircompanyRating = new AircompanyRating(aircompanyRatingDTO);
		aircompanyRatingRepository.save(aircompanyRating);
		aircompanyRatingDTO.setId(aircompanyRating.getId());
		
		return aircompanyRatingDTO;
	}
	
	public void deleteAircompanyRating(long id) {
		aircompanyRatingRepository.deleteById(id);
	}

	public double getRating(Long companyId) {
		List<AircompanyRatingDTO> aircompanies = new ArrayList<AircompanyRatingDTO>();
		
		double sum = 0;
		
		for (AircompanyRating c  : aircompanyRatingRepository.findAllById(companyId)) {
			sum += c.getRating();
			AircompanyRatingDTO airrating = new AircompanyRatingDTO(c);
			aircompanies.add(airrating);
 		}
		
		return sum / (aircompanies.size() * 1.0);
	}
	
	
}
