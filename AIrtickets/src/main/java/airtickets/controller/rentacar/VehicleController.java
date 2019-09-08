package airtickets.controller.rentacar;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.rentacar.VehicleDTO;
import airtickets.model.rentacar.CarType;
import airtickets.service.rentacar.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehicles")
public class VehicleController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/all")
	public List<VehicleDTO> getAllVehicles() {
		return vehicleService.getVehicles();
	}

	@GetMapping("/{id}")
	public VehicleDTO getById(@PathVariable Long id) {
		return vehicleService.getVehicle(id);
	}
	/*
	@GetMapping("/")
	public List<VehicleDTO> getByRentACarId(@RequestParam(value="rentACarId") Long rentACarId, @RequestParam(value="name") String name) {
//		Long l = (rentACarId == null) ? null : rentACarId;
//		log.info("Dali je null: " +  l);
		return vehicleService.getVehiclesByRentACarId(rentACarId, name);
	}
*/
	@PostMapping("/new")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicle) {
			return vehicleService.addVehicle(vehicle);
	}
	
	@PutMapping("makeReservation/{id}/{from}/{to}")
	public Long makeReservation(@RequestBody VehicleDTO vehicle, @PathVariable Long id, @PathVariable String from, @PathVariable String to) {
		return vehicleService.makeReservation(vehicle, id, from, to);
	}
	
	@PutMapping("makeQuickReservation/{reservationId}/{carReservationId}")
	public Long makeQuickReservation(@PathVariable Long reservationId, @PathVariable Long carReservationId) {
		return vehicleService.makeQuickReservation(reservationId, carReservationId);
	}
	
	@PutMapping("{id}/update")
	public VehicleDTO updateVehicle(@RequestBody VehicleDTO vehicle, @PathVariable Long id) {
		vehicle.setId(id);
		return vehicleService.addVehicle(vehicle);
	}

	@DeleteMapping("{id}/delete")
	public void deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicle(id);
	}
	
	@GetMapping("/search")
	public List<VehicleDTO> searchRentACars(
			@RequestParam(value="rentacarId") long rentacarId,
			@RequestParam(value="vehicleType") String type,
			@RequestParam(value="passengersSalji") int passangers,
			@RequestParam(value="priceFrom") double lowerPrice,
			@RequestParam(value="priceTo") double upperPrice,
			@RequestParam(value="pickupDateTime") String from,
			@RequestParam(value="dropoffDateTime") String to,
			@RequestParam(value="pickupLocation") String pickupLocation,
			@RequestParam(value="dropoffLocation") String dropoffLocation
			) {
		
		log.info(rentacarId + "");
		log.info(type);
		log.info(passangers + "");
		log.info(lowerPrice + "");
		log.info(upperPrice + "");
		log.info(from);
		log.info(to);
		
		return vehicleService.searchVehicles(rentacarId, type, passangers, lowerPrice, upperPrice, from, to);
	}
}
