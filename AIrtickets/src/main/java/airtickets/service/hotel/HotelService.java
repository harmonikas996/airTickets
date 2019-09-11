package airtickets.service.hotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.HotelDTO;
import airtickets.dto.hotel.RoomDTO;
import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.rentacar.RentacarWithBrachesDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.RentACar;
import airtickets.model.user.User;
import airtickets.repo.hotel.HotelRepository;
import airtickets.repo.hotel.RoomRepository;
import airtickets.repo.user.UserRepository;

@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<HotelDTO> getHotels(){
		List<HotelDTO> hotelsDTO = new ArrayList<HotelDTO>();
		List<Hotel> hotels = hotelRepository.findAll();
		
		for(Hotel h : hotels) {
			HotelDTO hotelDTO = new HotelDTO(h);
			hotelsDTO.add(hotelDTO);
		}
		return hotelsDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public HotelDTO getHotel(long id) {
		Hotel h = hotelRepository.findById(id);
		HotelDTO hotelDTO = new HotelDTO(h);
		
		return hotelDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public HotelDTO addHotel(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel(hotelDTO);
		hotelRepository.save(hotel);
		hotelDTO.setId(hotel.getId());
		
		return hotelDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteHotel(long id) {
		hotelRepository.deleteById(id);
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public HotelDTO getHotelByAdmin(String adminUsername) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());
		Hotel h  = hotelRepository.findById(user.getCompany().getId());
		HotelDTO hotel = new HotelDTO(h);
		
		return hotel;
	}

	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public List<Double> monthyIncome(long hotId, int year) {
		
		List<Double> incomes = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			
			LocalDateTime from;
			LocalDateTime to;
			
			if (i < 9) {				
				from = LocalDateTime.parse(year + "-0" + i + "-01T00:00:00");
				to = LocalDateTime.parse(year + "-0" + (i+1) + "-01T00:00:00");
			}
			else if (i == 9) {
				from = LocalDateTime.parse(year + "-09-01T00:00:00");
				to = LocalDateTime.parse(year + "-10-01T00:00:00");
			}
			else if (i < 12) {
				from = LocalDateTime.parse(year + "-" + i + "-01T00:00:00");
				to = LocalDateTime.parse(year + "-" + (i+1) + "-01T00:00:00");
			}
			else {
				from = LocalDateTime.parse(year + "-12-01T00:00:00");
				to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
			}
			
			Double d = hotelRepository.incomeForPeriod(hotId, from, to);
			
			incomes.add(d!=null ? d : 0);
		}
		
		return incomes;
	}

	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public List<Double> weeklyIncome(long hotId, int year) {
		
		List<Double> incomes = new ArrayList<>();
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse(year + "-01-08T00:00:00");
		
		for (int i = 1; i <= 52; i++) {
			Double d = hotelRepository.incomeForPeriod(hotId, from, to);
			incomes.add(d!=null ? d : 0);
			
			from = from.plusDays(7);
			to = to.plusDays(7);
		}
		to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		Double d = hotelRepository.incomeForPeriod(hotId, from, to);
		incomes.add(d!=null ? d : 0);
		
		return incomes;
	}

	@Transactional(readOnly = true, isolation=Isolation.REPEATABLE_READ)
	public double yearlyIncome(long hotId, int year) {
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		
		Double income = hotelRepository.incomeForPeriod(hotId, from, to);
		
		if (income == null)
			income = 0.0;
		
		return income;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomDTO> freeRoomsForPeriod(long id, String from, String to) {
		
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<RoomDTO> rooms = new ArrayList<>();
		
		for (Room r : roomRepository.freeRoomsForPeriod(id, df, dt)) {
			rooms.add(new RoomDTO(r));
		}
		
		return rooms;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomDTO> reservedRoomsForPeriod(long id, String from, String to) {
		
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<RoomDTO> rooms = new ArrayList<>();
		
		for (Room r : roomRepository.reservedRoomsForPeriod(id, df, dt)) {
			rooms.add(new RoomDTO(r));
		}
		
		return rooms;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomDTO> getRoomsFromHotel(long id) {
		List<RoomDTO> veh = new ArrayList<>();
		for (Room v  : roomRepository.findByHotelId(id)) {
			veh.add(new RoomDTO(v));
 		}
		return veh;
	}

	public boolean isCurrentlyReserved(long id) {
		// logika i upit da li sme da se brise soba, tj da li je rezervisana od ranije u odnosu na sad
		return false;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<HotelDTO> searchHotels(String name, String location, String timeBegin, String timeEnd) {
		
		LocalDateTime ldtFrom = LocalDateTime.parse(timeBegin);
		LocalDateTime ldtTo = LocalDateTime.parse(timeEnd);
		
		if(name.equals(" ") || name == null)
			name = "%%";
		if(location.equals(" ") || location == null)
			location = "%%";
		
		List<HotelDTO> hotels = new ArrayList<>();
		
		for (Hotel h  : hotelRepository.searchHotels(name, location, ldtFrom, ldtTo)) {
			HotelDTO hot = new HotelDTO(h);
			hotels.add(hot);
 		}
		
		return hotels;
	}

	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomDTO> searchRooms(long hotelId, String type, String timeBegin, String timeEnd) {
		
		LocalDateTime ldtFrom = LocalDateTime.parse(timeBegin);
		LocalDateTime ldtTo = LocalDateTime.parse(timeEnd);
		
		List<RoomDTO> rooms = new ArrayList<>();
		
		for (Room r  : roomRepository.searchRooms(hotelId, type, ldtFrom, ldtTo)) {
			RoomDTO room = new RoomDTO(r);
			rooms.add(room);
 		}
		
		return null;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		
		for (String b  : hotelRepository.findAllCities()) {
			locations.add(b);
 		}
		return locations;
	}

	public List<RoomDTO> freeRoomsForPeriodWithBeds(long id, String from, String to, String beds) {
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<RoomDTO> rooms = new ArrayList<>();
		
		for (Room r : roomRepository.freeRoomsForPeriodWithBeds(id, df, dt, beds)) {
			rooms.add(new RoomDTO(r));
		}
		
		return rooms;
	}
}
