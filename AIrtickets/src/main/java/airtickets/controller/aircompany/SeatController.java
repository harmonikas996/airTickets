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

import airtickets.dto.aircompany.SeatDTO;
import airtickets.service.aircompany.SeatService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	@GetMapping("/all")
	public List<SeatDTO> getAllSeats(){
		return seatService.getSeats();
	}
	
	@GetMapping("/{id}")
	public SeatDTO getById(@PathVariable Long id){
		return seatService.getSeat(id);
	}
	
	@PostMapping("/new")
	public SeatDTO addSeat(@RequestBody SeatDTO seat) {
		return seatService.addSeat(seat);
	}
	
	@PutMapping("{id}/update")
	public SeatDTO updateSeat(@RequestBody SeatDTO seat, @PathVariable Long id) {
		seat.setId(id);
		return seatService.addSeat(seat);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteSeat(@PathVariable Long id) {
		seatService.deleteSeat(id);
	}
	
}