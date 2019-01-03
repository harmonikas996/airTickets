package airtickets.controller.aircompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.aircompany.AirportDTO;
import airtickets.service.aircompany.AirportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/airports")
public class AirportController {
	
	@Autowired
	private AirportService airportService;
	
	@GetMapping("/all")
	public List<AirportDTO> getAllAirports(){
		return airportService.getAirports();
	}
	
	@GetMapping("/{id}")
	public AirportDTO getById(@PathVariable Long id){
		return airportService.getAirport(id);
	}
	
	@PostMapping("/new")
	public AirportDTO addAirport(@RequestBody AirportDTO airport) {
		return airportService.addAirport(airport);
	}
	
	@PutMapping("{id}/update")
	public AirportDTO updateAirport(@RequestBody AirportDTO airport, @PathVariable Long id) {
		airport.setId(id);
		return airportService.addAirport(airport);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteAirport(@PathVariable Long id) {
		airportService.deleteAircompany(id);
	}
	
}
