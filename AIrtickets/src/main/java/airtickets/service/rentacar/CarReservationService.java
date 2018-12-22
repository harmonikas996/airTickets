package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.CarReservationDTO;
import airtickets.model.rentacar.CarReservation;
import airtickets.repo.rentacar.CarReservationRepository;

@Service
public class CarReservationService {
	
	@Autowired
	CarReservationRepository carReservationRepository;

	public List<CarReservationDTO> getCarReservations() {
		List<CarReservationDTO> carReservations = new ArrayList<CarReservationDTO>();
		
		for (CarReservation c  : carReservationRepository.findAll()) {
			CarReservationDTO car = new CarReservationDTO(c);
			carReservations.add(car);
 		}
		return carReservations;
	}

	public CarReservationDTO getCarReservation(long id) {
		CarReservation c  = carReservationRepository.findById(id);
		CarReservationDTO carReservation = new CarReservationDTO(c);
		return carReservation;
	}

	public CarReservationDTO addCarReservation(CarReservationDTO carReservationDTO) {
		CarReservation carReservation = new CarReservation(carReservationDTO);
		carReservationRepository.save(carReservation);
		carReservationDTO.setId(carReservation.getId());
		return carReservationDTO;
	}

	public void deleteCarReservation(long id) {
		carReservationRepository.deleteById(id);
	}
	
}
