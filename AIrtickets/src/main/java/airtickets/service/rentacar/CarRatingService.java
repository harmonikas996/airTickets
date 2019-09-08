package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.RentACarRating;
import airtickets.repo.rentacar.CarRatingRepository;

@Service
public class CarRatingService {
	
	@Autowired
	CarRatingRepository carRatingRepository;
	
	public List<CarRatingDTO> getCarRatings() {
		List<CarRatingDTO> carRatings = new ArrayList<CarRatingDTO>();
		
		for (CarRating c  : carRatingRepository.findAll()) {
			CarRatingDTO carRating = new CarRatingDTO(c);
			carRatings.add(carRating);
 		}
		return carRatings;
	}
	
	public CarRatingDTO getRatingByVehicleId(long id) {
		CarRating c = carRatingRepository.findByVehicleId(id);
		
		if( c == null) {
			return new CarRatingDTO();
		}
		
		CarRatingDTO car = new CarRatingDTO(c);
		
		return car;
	}

	public CarRatingDTO getCarRating(long id) {
		CarRating c  = carRatingRepository.findById(id);
		CarRatingDTO carRating = new CarRatingDTO(c);
		return carRating;
	}

	public CarRatingDTO addCarRating(CarRatingDTO carRatingDTO) {
		CarRating carRating = new CarRating(carRatingDTO);
		carRatingRepository.save(carRating);
		carRatingDTO.setId(carRating.getId());
		return carRatingDTO;
	}

	public void deleteCarRating(long id) {
		carRatingRepository.deleteById(id);
	}

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
