package airtickets.controller.rentacar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.rentacar.VehicleDTO;
import airtickets.service.rentacar.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehicles")
public class VehicleController {

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

	@PostMapping("/new")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicle) {
			return vehicleService.addVehicle(vehicle);
	}

	@PutMapping("{id}/update")
	public VehicleDTO updateVehicle(@RequestBody VehicleDTO vehicle) {
		return vehicleService.addVehicle(vehicle);
	}
}
