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

import airtickets.dto.aircompany.StopDTO;
import airtickets.service.aircompany.StopService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/stops")
public class StopController {

	@Autowired
	private StopService stopService;
	
	@GetMapping("/all")
	public List<StopDTO> getAllStops(){
		return stopService.getStops();
	}
	
	@GetMapping("/{id}")
	public StopDTO getById(@PathVariable Long id){
		return stopService.getStop(id);
	}
	
	@PostMapping("/new")
	public StopDTO addStop(@RequestBody StopDTO stop) {
		return stopService.addStop(stop);
	}
	
	@PutMapping("{id}/update")
	public StopDTO updateStop(@RequestBody StopDTO stop, @PathVariable Long id) {
		stop.setId(id);
		return stopService.addStop(stop);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteStop(@PathVariable Long id) {
		stopService.deleteStop(id);
	}
	
}
