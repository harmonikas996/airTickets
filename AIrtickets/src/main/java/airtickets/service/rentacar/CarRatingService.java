package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.RentACarRating;
import airtickets.repo.rentacar.CarRatingRepository;

@Service
public class CarRatingService {
	
	@Autowired
	CarRatingRepository carRatingRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<CarRatingDTO> getCarRatings() {
		List<CarRatingDTO> carRatings = new ArrayList<CarRatingDTO>();
		
		for (CarRating c  : carRatingRepository.findAll()) {
			CarRatingDTO carRating = new CarRatingDTO(c);
			carRatings.add(carRating);
 		}
		return carRatings;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public CarRatingDTO getRatingByVehicleId(long id) {
		CarRating c = carRatingRepository.findByVehicleId(id);
		
		if( c == null) {
			return new CarRatingDTO();
		}
		
		CarRatingDTO car = new CarRatingDTO(c);
		
		return car;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public CarRatingDTO getCarRating(long id) {
		CarRating c  = carRatingRepository.findById(id);
		CarRatingDTO carRating = new CarRatingDTO(c);
		return carRating;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public CarRatingDTO addCarRating(CarRatingDTO carRatingDTO) {
		CarRating carRating = new CarRating(carRatingDTO);
		carRatingRepository.save(carRating);
		carRatingDTO.setId(carRating.getId());
		return carRatingDTO;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteCarRating(long id) {
		carRatingRepository.deleteById(id);
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public double getRating(Long vehicleId) {
		List<CarRatingDTO> carRatings = new ArrayList<CarRatingDTO>();
		
		double sum = 0;
		
		for (CarRating c  : carRatingRepository.findAllById(vehicleId)) {
			sum += c.getRating();
			CarRatingDTO carRating = new CarRatingDTO(c);
			carRatings.add(carRating);
 		}
		
		return sum / (carRatings.size() * 1.0);
	}
}
