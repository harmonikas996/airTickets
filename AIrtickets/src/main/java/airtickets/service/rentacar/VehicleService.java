package airtickets.service.rentacar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.VehicleDTO;
import airtickets.model.rentacar.CarReservation;
import airtickets.model.rentacar.Vehicle;
import airtickets.repo.rentacar.CarReservationRepository;
import airtickets.repo.rentacar.VehiclesRepository;

@Service
public class VehicleService {

	@Autowired
	VehiclesRepository vehicleRepository;
	@Autowired
	CarReservationRepository carReservationRepository;

	public List<VehicleDTO> getVehicles() {
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		
		for (Vehicle v  : vehicleRepository.findAll()) {
			VehicleDTO vehicle = new VehicleDTO(v);
			vehicles.add(vehicle);
 		}
		return vehicles;
	}

	public VehicleDTO getVehicle(long id) {
		Vehicle v  = vehicleRepository.findById(id);
		VehicleDTO vehicle = new VehicleDTO(v);
		return vehicle;
	}

	public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle(vehicleDTO);
		vehicleRepository.save(vehicle);
		vehicleDTO.setId(vehicle.getId());
		return vehicleDTO;
	}

	public void deleteVehicle(long id) {
		
		List<CarReservation> reservations = carReservationRepository.findByVehicleId(id);
		
		for(CarReservation c : reservations)
			if(!c.getDateTo().isBefore(LocalDateTime.now()))
				return;
		
		vehicleRepository.deleteById(id);
	}
}
