package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.model.aircompany.FlightRating;
import airtickets.model.rentacar.RentACarRating;
import airtickets.repo.rentacar.RentACarRatingRepository;

@Service
public class RentACarRatingService {
	
	@Autowired
	RentACarRatingRepository rentACarRatingRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RentACarRatingDTO> getRentACarRatings() {
		List<RentACarRatingDTO> rentACarRatings = new ArrayList<RentACarRatingDTO>();
		
		for (RentACarRating c  : rentACarRatingRepository.findAll()) {
			RentACarRatingDTO rentACarRating = new RentACarRatingDTO(c);
			rentACarRatings.add(rentACarRating);
 		}
		return rentACarRatings;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public RentACarRatingDTO getRentACarRating(long id) {
		RentACarRating c  = rentACarRatingRepository.findById(id);
		RentACarRatingDTO rentACarRating = new RentACarRatingDTO(c);
		return rentACarRating;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public RentACarRatingDTO getRatingByRentACarId(long id) {
		RentACarRating c = rentACarRatingRepository.findByRentACarId(id);
		
		if( c == null) {
			return new RentACarRatingDTO();
		}
		
		RentACarRatingDTO rentacar = new RentACarRatingDTO(c);
		
		return rentacar;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public RentACarRatingDTO addRentACarRating(RentACarRatingDTO rentACarRatingDTO) {
		RentACarRating rentcarRating = new RentACarRating(rentACarRatingDTO);
		rentACarRatingRepository.save(rentcarRating);
		rentACarRatingDTO.setId(rentcarRating.getId());
		return rentACarRatingDTO;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteRentACarRating(long id) {
		rentACarRatingRepository.deleteById(id);
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public double getRating(long rentACarId) {
		List<RentACarRatingDTO> rentACarRatings = new ArrayList<RentACarRatingDTO>();
		
		double sum = 0;
		
		for (RentACarRating c  : rentACarRatingRepository.findAllById(rentACarId)) {
			sum += c.getRating();
			RentACarRatingDTO rentACarRating = new RentACarRatingDTO(c);
			rentACarRatings.add(rentACarRating);
 		}
		
		return sum / (rentACarRatings.size() * 1.0);
	}
}
