package airtickets.service.hotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.RoomDTO;
import airtickets.dto.rentacar.VehicleDTO;
import airtickets.model.hotel.Room;
import airtickets.model.rentacar.Vehicle;
import airtickets.model.user.User;
import airtickets.repo.hotel.RoomRepository;
import airtickets.repo.user.UserRepository;

@Service
public class RoomService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<RoomDTO> getRooms(){
		List<RoomDTO> roomsDTO = new ArrayList<RoomDTO>();
		List<Room> rooms = roomRepository.findAll();
		
		for(Room r : rooms) {
			RoomDTO roomDTO = new RoomDTO(r);
			roomsDTO.add(roomDTO);
		}
		return roomsDTO;
	}
	
	public List<RoomDTO> getRoomsByHotel(long id){
		List<RoomDTO> roomsDTO = new ArrayList<RoomDTO>();
		List<Room> rooms = roomRepository.getRoomsByHotel(id);
		
		for(Room r : rooms) {
			RoomDTO roomDTO = new RoomDTO(r);
			roomsDTO.add(roomDTO);
		}
		return roomsDTO;
	}
	
	public RoomDTO getRoom(long id) {
		Room r = roomRepository.findById(id);
		RoomDTO roomDTO = new RoomDTO(r);
		
		return roomDTO;
	}
	
	public RoomDTO addRoom(RoomDTO roomDTO) {
		//log.info("ROOM HOTELID: " + roomDTO.getHotelId());
		Room room = new Room(roomDTO);
		roomRepository.save(room);
		roomDTO.setId(room.getId());
		
		return roomDTO;
	}
	
	public void deleteRoom(long id) {
		roomRepository.deleteById(id);
	}
	
	public List<RoomDTO> freeRoomsForPeriod(long id, String from, String to) {
		
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<RoomDTO> veh = new ArrayList<>();
		
		for (Room v : roomRepository.freeRoomsForPeriod(id, df, dt)) {
			veh.add(new RoomDTO(v));
		}
		
		return veh;
	}
	
	public List<RoomDTO> getFreeRooms(String email, String from, String to) {
		User user = userRepository.findByEmail(email);
		
		return freeRoomsForPeriod(user.getCompany().getId(), from, to);
	}
}
