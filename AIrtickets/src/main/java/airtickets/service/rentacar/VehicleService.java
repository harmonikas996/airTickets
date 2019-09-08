package airtickets.service.rentacar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		log.info(vehicle.getType() + "");
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
		
		log.info(rentacarId + "");
		log.info(ct + "");
		log.info(passangers + "");
		log.info(lowerPrice + "");
		log.info(upperPrice + "");
		log.info(ldtFrom + "");
		log.info(ldtTo + "");
		
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>();
		
		for (Vehicle v  : vehicleRepository.searchVehicles(rentacarId, ct, passangers, lowerPrice, upperPrice, ldtFrom, ldtTo)) {
			log.info("EHEJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
			VehicleDTO vehicle = new VehicleDTO(v);
			vehicles.add(vehicle);
 		}
		return vehicles;
	}
	
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

	public Long makeQuickReservation(long reservationId, long carReservationId) {		
		CarReservation cr = carReservationRepository.findById(carReservationId);
		FlightReservation fr = flightReservationRepository.findById(reservationId);
		fr.setCarReservation(cr);
		flightReservationRepository.save(fr);
		return cr.getId();
	}
}
