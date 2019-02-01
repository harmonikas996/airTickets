package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.RoomReservationDTO;
import airtickets.model.hotel.RoomReservation;
import airtickets.repo.hotel.RoomReservationRepository;

public class RoomReservationService {

	@Autowired
	RoomReservationRepository roomReservationRepository;
	
	public List<RoomReservationDTO> getRoomReservations(){
		List<RoomReservationDTO> roomReservationsDTO = new ArrayList<RoomReservationDTO>();
		List<RoomReservation> roomReservations = roomReservationRepository.findAll();
		
		for(RoomReservation r : roomReservations) {
			RoomReservationDTO roomReservationDTO = new RoomReservationDTO(r);
			roomReservationsDTO.add(roomReservationDTO);
		}
		return roomReservationsDTO;
	}
	
	public RoomReservationDTO getRoomReservation(long id) {
		RoomReservation r = roomReservationRepository.findById(id);
		RoomReservationDTO roomReservationDTO = new RoomReservationDTO(r);
		
		return roomReservationDTO;
	}
	
	public RoomReservationDTO addRoomReservation(RoomReservationDTO roomReservationDTO) {
		RoomReservation roomReservation = new RoomReservation(roomReservationDTO);
		roomReservationRepository.save(roomReservation);
		roomReservationDTO.setId(roomReservation.getId());
		
		return roomReservationDTO;
	}
	
	public void deleteRoomReservation(long id) {
		roomReservationRepository.deleteById(id);
	}
}
