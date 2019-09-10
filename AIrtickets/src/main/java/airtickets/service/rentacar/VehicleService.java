package airtickets.service.rentacar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.rentacar.VehicleDTO;
import airtickets.model.aircompany.FlightReservation;
import airtickets.model.rentacar.CarReservation;
import airtickets.model.rentacar.Vehicle;
import airtickets.repo.aircompany.FlightReservationRepository;
import airtickets.repo.rentacar.CarReservationRepository;
import airtickets.repo.rentacar.VehiclesRepository;

@Service
public class VehicleService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	VehiclesRepository vehicleRepository;
	@Autowired
	CarReservationRepository carReservationRepository;
	@Autowired
	FlightReservationRepository flightReservationRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<VehicleDTO> getVehicles() {
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		
		for (Vehicle v  : vehicleRepository.findAll()) {
			VehicleDTO vehicle = new VehicleDTO(v);
			vehicles.add(vehicle);
 		}
		return vehicles;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public VehicleDTO getVehicle(long id) {
		Vehicle v  = vehicleRepository.findById(id);
		VehicleDTO vehicle = new VehicleDTO(v);
		return vehicle;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle(vehicleDTO);
		vehicleRepository.save(vehicle);
		vehicleDTO.setId(vehicle.getId());
		return vehicleDTO;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteVehicle(long id) {
		
		List<CarReservation> reservations = carReservationRepository.findByVehicleId(id);
		
		for(CarReservation c : reservations)
			if(!c.getDateTo().isBefore(LocalDateTime.now()))
				return;
		
		vehicleRepository.deleteById(id);
	}
	/*
	public List<VehicleDTO> getVehiclesByRentACarId(long id, String name) {
	
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		
		for (Vehicle v  : vehicleRepository.findByRentACarId(id, name)) {
			VehicleDTO vehicle = new VehicleDTO(v);
			vehicles.add(vehicle);
 		}
		return vehicles;
	}
	*/
	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public List<VehicleDTO> searchVehicles(long rentacarId, String type, int passangers, 
			double lowerPrice, double upperPrice, String from, String to) {
		
		LocalDateTime ldtFrom = LocalDateTime.parse(from);
		LocalDateTime ldtTo = LocalDateTime.parse(to);
		
		if(lowerPrice == -1)
			lowerPrice = 0;
		if(upperPrice == -1)
			upperPrice = 999999;
		
		int ct;
		
		if (type.equals("Sedan"))
			ct = 0;
		else if (type.equals("Station Wagon"))
			ct = 1;
		else if (type.equals("Van"))
			ct = 2;
		else
			ct = 3;
		
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		
		for (Vehicle v  : vehicleRepository.searchVehicles(rentacarId, ct, passangers, lowerPrice, upperPrice, ldtFrom, ldtTo)) {
			VehicleDTO vehicle = new VehicleDTO(v);
			vehicles.add(vehicle);
 		}
		return vehicles;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public Long makeReservation(VehicleDTO vehicle, long id, String from, String to) {
		LocalDateTime ldtFrom = LocalDateTime.parse(from);
		LocalDateTime ldtTo = LocalDateTime.parse(to);
		
		int brDana = (int) ChronoUnit.DAYS.between(ldtFrom.toLocalDate(), ldtTo.toLocalDate());// .daysBetween();
		
		CarReservation cr = new CarReservation();
		cr.setDateFrom(ldtFrom);
		cr.setDateTo(ldtTo);
		Vehicle v = vehicleRepository.findById(vehicle.getId());
		cr.setPrice((++brDana)*v.getPricePerDay());
		cr.setVehicle(v);
		cr = carReservationRepository.save(cr);
		FlightReservation fr = flightReservationRepository.findById(id);
		fr.setCarReservation(cr);
		flightReservationRepository.save(fr);
		return cr.getId();
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public Long makeQuickReservation(long reservationId, long carReservationId) {		
		CarReservation cr = carReservationRepository.findById(carReservationId);
		FlightReservation fr = flightReservationRepository.findById(reservationId);
		fr.setCarReservation(cr);
		flightReservationRepository.save(fr);
		return cr.getId();
	}
}
