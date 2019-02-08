package airtickets.controller.hotel;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.hotel.RoomDTO;
import airtickets.dto.hotel.RoomReservationDTO;
import airtickets.service.hotel.RoomReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/roomReservations")
public class RoomReservationController {

	@Autowired
	private RoomReservationService roomReservationService;
	
	@GetMapping("/all")
	public List<RoomReservationDTO> getAllRoomReservations(){
		return roomReservationService.getRoomReservations();
	}
	
	@GetMapping("/{id}")
	public RoomReservationDTO getById(@PathVariable Long id){
		return roomReservationService.getRoomReservation(id);
	}
	
	@PostMapping("/new")
	public RoomReservationDTO addRoomReservation(@RequestBody RoomReservationDTO roomReservation) {
		return roomReservationService.addRoomReservation(roomReservation);
	}
	
	@PutMapping("{id}/update")
	public RoomReservationDTO updateRoomReservation(@RequestBody RoomReservationDTO roomReservation, @PathVariable Long id) {
		roomReservation.setId(id);
		return roomReservationService.addRoomReservation(roomReservation);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteRoomReservation(@PathVariable Long id) {
		roomReservationService.deleteRoomReservation(id);
	}
	
	@GetMapping("/addQuick")
//	@PreAuthorize("hasAuthority('rentacar')")
	public RoomReservationDTO addQuick(
			@RequestParam(value="user") String email,
			@RequestParam(value="timeBegin") String from,
			@RequestParam(value="timeEnd") String to,
			@RequestParam(value="roomId") long id,
			@RequestParam(value="price") double price
			) {
		
		return roomReservationService.addQuick(email, from, to, id, price);
	}
}
