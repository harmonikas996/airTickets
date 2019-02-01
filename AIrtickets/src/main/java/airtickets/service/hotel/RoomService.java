package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.RoomDTO;
import airtickets.model.hotel.Room;
import airtickets.repo.hotel.RoomRepository;

public class RoomService {

	@Autowired
	RoomRepository roomRepository;
	
	public List<RoomDTO> getRooms(){
		List<RoomDTO> roomsDTO = new ArrayList<RoomDTO>();
		List<Room> rooms = roomRepository.findAll();
		
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
		Room room = new Room(roomDTO);
		roomRepository.save(room);
		roomDTO.setId(room.getId());
		
		return roomDTO;
	}
	
	public void deleteRoom(long id) {
		roomRepository.deleteById(id);
	}
}
