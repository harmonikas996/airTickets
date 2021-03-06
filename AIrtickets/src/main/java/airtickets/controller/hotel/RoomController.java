package airtickets.controller.hotel;

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

import airtickets.dto.hotel.RoomDTO;
import airtickets.dto.rentacar.VehicleDTO;
import airtickets.service.hotel.RoomService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/rooms")
public class RoomController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/all")
	public List<RoomDTO> getAllRooms(){
		return roomService.getRooms();
	}
	
	@GetMapping("hotel/{id}")
	public List<RoomDTO> getRoomsByHotel(@PathVariable Long id){
		return roomService.getRoomsByHotel(id);
	}
	
	@GetMapping("/{id}")
	public RoomDTO getById(@PathVariable Long id){
		return roomService.getRoom(id);
	}
	
	@PostMapping("/new")
	public RoomDTO addRoom(@RequestBody RoomDTO room) {
		return roomService.addRoom(room);
	}
	
	@PutMapping("{id}/update")
	public RoomDTO updateRoom(@RequestBody RoomDTO room, @PathVariable Long id) {
		room.setId(id);
		return roomService.addRoom(room);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteRoom(@PathVariable Long id) {
		roomService.deleteRoom(id);
	}
	
	@GetMapping("/free")
//	@PreAuthorize("hasAuthority('rentacar')")
	public List<RoomDTO> isCurrentlyReserved(
			@RequestParam(value="user") String email,
			@RequestParam(value="datebegin") String from,
			@RequestParam(value="dateEnd") String to
			) {
		
		return roomService.getFreeRooms(email, from, to);
	}
}
