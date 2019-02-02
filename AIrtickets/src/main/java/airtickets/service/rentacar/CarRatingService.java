package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.model.rentacar.CarRating;
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
}
