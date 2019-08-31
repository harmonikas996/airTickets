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
import airtickets.dto.hotel.RoomPriceDTO;
import airtickets.service.hotel.RoomPriceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/roomPrices")
public class RoomPriceController {

	@Autowired
	private RoomPriceService roomPriceService;
	
	@GetMapping("/all")
	public List<RoomPriceDTO> getAllRoomPrices(){
		return roomPriceService.getRoomPrices();
	}
	
	@GetMapping("/hotel/{id}")
	public List<RoomPriceDTO> getRoomPricesByHotel(@PathVariable Long id){
		return roomPriceService.getRoomPricesByHotel(id);
	}
	
	@GetMapping("/{id}")
	public RoomPriceDTO getById(@PathVariable Long id){
		return roomPriceService.getRoomPrice(id);
	}
	
	@PostMapping("/new")
	public RoomPriceDTO addRoom(@RequestBody RoomPriceDTO roomPrice) {
		return roomPriceService.addRoomPrice(roomPrice);
	}
	
	@PutMapping("{id}/update")
	public RoomPriceDTO updateRoom(@RequestBody RoomPriceDTO roomPrice, @PathVariable Long id) {
		roomPrice.setId(id);
		return roomPriceService.addRoomPrice(roomPrice);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteRoom(@PathVariable Long id) {
		roomPriceService.deleteRoomPrice(id);
	}
	
	@GetMapping("/search")
	public RoomPriceDTO searchRoomPriceForDateRange(
			@RequestParam(value="roomId") Long roomId,
			@RequestParam(value="datoFrom") String datoFrom,
			@RequestParam(value="datoTo") String datoTo){
		return roomPriceService.searchRoomPriceForDateRange(roomId, datoFrom, datoTo);
	}
}
