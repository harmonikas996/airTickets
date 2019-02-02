package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.model.rentacar.RentACarRating;
import airtickets.repo.rentacar.RentACarRatingRepository;

@Service
public class RentACarRatingService {
	
	@Autowired
	RentACarRatingRepository rentACarRatingRepository;
	
	public List<RentACarRatingDTO> getRentACarRatings() {
		List<RentACarRatingDTO> rentACarRatings = new ArrayList<RentACarRatingDTO>();
		
		for (RentACarRating c  : rentACarRatingRepository.findAll()) {
			RentACarRatingDTO rentACarRating = new RentACarRatingDTO(c);
			rentACarRatings.add(rentACarRating);
 		}
		return rentACarRatings;
	}

	public RentACarRatingDTO getRentACarRating(long id) {
		RentACarRating c  = rentACarRatingRepository.findById(id);
		RentACarRatingDTO rentACarRating = new RentACarRatingDTO(c);
		return rentACarRating;
	}

	public RentACarRatingDTO addRentACarRating(RentACarRatingDTO rentACarRatingDTO) {
		RentACarRating rentcarRating = new RentACarRating(rentACarRatingDTO);
		rentACarRatingRepository.save(rentcarRating);
		rentACarRatingDTO.setId(rentcarRating.getId());
		return rentACarRatingDTO;
	}

	public void deleteRentACarRating(long id) {
		rentACarRatingRepository.deleteById(id);
	}
}
