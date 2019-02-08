package airtickets.controller.rentacar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.rentacar.RentacarWithBrachesDTO;
import airtickets.dto.rentacar.VehicleDTO;
import airtickets.service.rentacar.RentACarService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/rentacars")
public class RentACarController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RentACarService rentACarService;
	
	@GetMapping("/all")
	public List<RentACarDTO> getAllRentACars() {
		return rentACarService.getRentACars();
	}

	@GetMapping("/{id}")
	public RentACarDTO getById(@PathVariable Long id) {
		return rentACarService.getRentACar(id);
	}

	@PostMapping("/new")
    public RentACarDTO addRentACar(@RequestBody RentACarDTO rentACar) {
			return rentACarService.addRentACar(rentACar);
	}

	@PutMapping("{id}/update")
	public RentACarDTO updateRentACar(@RequestBody RentACarDTO rentACar, @PathVariable Long id) {
		rentACar.setId(id);
		return rentACarService.addRentACar(rentACar);
	}

	@DeleteMapping("{id}/delete")
	public void deleteRentACar(@PathVariable Long id) {
		rentACarService.deleteRentACar(id);
	}
	
	@GetMapping("/admin/{username}")
	@PreAuthorize("hasAuthority('rentacar')")
	public RentACarDTO getByAdminUsername(@PathVariable String username) {
		return rentACarService.getRentACarByAdmin(username);
	}
	
	@GetMapping("/search")
	public List<RentacarWithBrachesDTO> searchRentACars(
			@RequestParam(value="name") String name,
			@RequestParam(value="location") String location,
			@RequestParam(value="timeBegin") String timeBegin,
			@RequestParam(value="timeEnd") String timeEnd
			) {
		
		log.info(name);
		log.info(location);
		log.info(timeBegin);
		log.info(timeEnd);
		
		return rentACarService.searchRentACars(name, location, timeBegin, timeEnd);
	}

	@GetMapping("/monthlyIncome")
	@PreAuthorize("hasAuthority('rentacar')")
	public List<Double> monthyIncome(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="year") int year
			) {
		
		return rentACarService.monthyIncome(rcrId, year);
	}

	@GetMapping("/weeklyIncome")
	@PreAuthorize("hasAuthority('rentacar')")
	public List<Double> weeklyIncome(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="year") int year
			) {
		
		return rentACarService.weeklyIncome(rcrId, year);
	}

	@GetMapping("/yearlyIncome")
	@PreAuthorize("hasAuthority('rentacar')")
	public double yearlyIncome(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="year") int year
			) {
		
		return rentACarService.yearlyIncome(rcrId, year);
	}

	@GetMapping("/freeVehicles")
//	@PreAuthorize("hasAuthority(rentacar)")
	public List<VehicleDTO> freeVehicles(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="dateBegin") String timeBegin,
			@RequestParam(value="dateEnd") String timeEnd
			) {
		
		return rentACarService.freeVehiclesForPeriod(rcrId, timeBegin, timeEnd);
	}

	@GetMapping("/reservedVehicles")
//	@PreAuthorize("hasAuthority(rentacar)")
	public List<VehicleDTO> reservedVehicles(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="dateBegin") String timeBegin,
			@RequestParam(value="dateEnd") String timeEnd
			) {
		
		return rentACarService.reservedVehiclesForPeriod(rcrId, timeBegin, timeEnd);
	}

	@GetMapping("/carsFromRentacar")
//	@PreAuthorize("hasAuthority(rentacar)")
	public List<VehicleDTO> getCarsFromRentacar(
			@RequestParam(value="id") long rcrId
			) {
		
		return rentACarService.getCarsFromRentacar(rcrId);
	}

	@GetMapping("/isCurrentlyReserved")
//	@PreAuthorize("hasAuthority('rentacar')")
	public boolean isCurrentlyReserved(
			@RequestParam(value="id") long id,
			@RequestParam(value="rentacarId") long rcrId
			) {
		
		return rentACarService.isCurrentlyReserved(id);
	}
}
