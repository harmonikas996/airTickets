package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.rentacar.CarReservationDTO;
import airtickets.model.rentacar.CarReservation;
import airtickets.repo.rentacar.CarReservationRepository;

@Service
public class CarReservationService {
	
	@Autowired
	CarReservationRepository carReservationRepository;

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<CarReservationDTO> getCarReservations() {
		List<CarReservationDTO> carReservations = new ArrayList<CarReservationDTO>();
		
		for (CarReservation c  : carReservationRepository.findAll()) {
			CarReservationDTO car = new CarReservationDTO(c);
			carReservations.add(car);
 		}
		return carReservations;
	}

	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public List<CarReservationDTO> getCarReservationsByUser(Long id) {
		List<CarReservationDTO> carReservations = new ArrayList<CarReservationDTO>();
		
		for (CarReservation c  : carReservationRepository.getCarReservationsByUser(id)) {
			CarReservationDTO car = new CarReservationDTO(c);
			carReservations.add(car);
 		}
		return carReservations;
	}

	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public List<CarReservationDTO> getQuickCarReservationsByCompanyId(long id) {
		List<CarReservationDTO> carReservations = new ArrayList<CarReservationDTO>();
		
		for (CarReservation c  : carReservationRepository.getQuickCarReservationsByCompanyId(id)) {
			CarReservationDTO car = new CarReservationDTO(c);
			carReservations.add(car);
 		}
		return carReservations;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public CarReservationDTO getCarReservation(long id) {
		CarReservation c  = carReservationRepository.findById(id);
		CarReservationDTO carReservation = new CarReservationDTO(c);
		return carReservation;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public CarReservationDTO addCarReservation(CarReservationDTO carReservationDTO) {
		CarReservation carReservation = new CarReservation(carReservationDTO);
		carReservationRepository.save(carReservation);
		carReservationDTO.setId(carReservation.getId());
		return carReservationDTO;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteCarReservation(long id) {
		carReservationRepository.deleteById(id);
	}
}
