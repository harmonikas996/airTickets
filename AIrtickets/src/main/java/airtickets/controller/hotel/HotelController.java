package airtickets.controller.hotel;

import java.util.List;

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

import airtickets.dto.hotel.HotelDTO;
import airtickets.dto.hotel.RoomDTO;
import airtickets.dto.rentacar.VehicleDTO;
import airtickets.service.hotel.HotelService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@GetMapping("/all")
	public List<HotelDTO> getAllHotels(){
		return hotelService.getHotels();
	}

	@GetMapping("/{id}")
	public HotelDTO getById(@PathVariable Long id){
		return hotelService.getHotel(id);
	}

	@PostMapping("/new")
	public HotelDTO addHotel(@RequestBody HotelDTO hotel) {
		return hotelService.addHotel(hotel);
	}

	@PutMapping("{id}/update")
	public HotelDTO updateHotel(@RequestBody HotelDTO hotel, @PathVariable Long id) {
		hotel.setId(id);
		return hotelService.addHotel(hotel);
	}

	@DeleteMapping("{id}/delete")
	public void deleteHotel(@PathVariable Long id) {
		hotelService.deleteHotel(id);
	}

	@GetMapping("/admin/{username}")
	@PreAuthorize("hasAuthority('hotel')")
	public HotelDTO getByAdminUsername(@PathVariable String username) {
		return hotelService.getHotelByAdmin(username);
	}

	@GetMapping("/monthlyIncome")
	@PreAuthorize("hasAuthority('hotel')")
	public List<Double> monthyIncome(
			@RequestParam(value="id") long hotId,
			@RequestParam(value="year") int year
			) {
		
		return hotelService.monthyIncome(hotId, year);
	}

	@GetMapping("/weeklyIncome")
	@PreAuthorize("hasAuthority('hotel')")
	public List<Double> weeklyIncome(
			@RequestParam(value="id") long hotId,
			@RequestParam(value="year") int year
			) {
		
		return hotelService.weeklyIncome(hotId, year);
	}

	@GetMapping("/yearlyIncome")
	@PreAuthorize("hasAuthority('hotel')")
	public double yearlyIncome(
			@RequestParam(value="id") long hotId,
			@RequestParam(value="year") int year
			) {
		
		return hotelService.yearlyIncome(hotId, year);
	}

	@GetMapping("/freeRooms")
//	@PreAuthorize("hasAuthority(rentacar)")
	public List<RoomDTO> freeVehicles(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="dateBegin") String timeBegin,
			@RequestParam(value="dateEnd") String timeEnd
			) {
		
		return hotelService.freeRoomsForPeriod(rcrId, timeBegin, timeEnd);
	}

	@GetMapping("/reservedRooms")
//	@PreAuthorize("hasAuthority(rentacar)")
	public List<RoomDTO> reservedVehicles(
			@RequestParam(value="id") long rcrId,
			@RequestParam(value="dateBegin") String timeBegin,
			@RequestParam(value="dateEnd") String timeEnd
			) {
		
		return hotelService.reservedRoomsForPeriod(rcrId, timeBegin, timeEnd);
	}

	@GetMapping("/roomsFromHotel")
//	@PreAuthorize("hasAuthority(hotel)")
	public List<RoomDTO> getCarsFromRentacar(
			@RequestParam(value="id") long hotId
			) {
		
		return hotelService.getRoomsFromHotel(hotId);
	}

	@GetMapping("/isCurrentlyReserved")
//	@PreAuthorize("hasAuthority(hotel)")
	public boolean isCurrentlyReserved(
			@RequestParam(value="id") long id,
			@RequestParam(value="hotelId") long hotId
			) {
		
		return hotelService.isCurrentlyReserved(id);
	}
}
