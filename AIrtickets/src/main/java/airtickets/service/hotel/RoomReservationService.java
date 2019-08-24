package airtickets.service.hotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.hotel.RoomReservationDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.HotelReservation;
import airtickets.model.hotel.Room;
import airtickets.model.hotel.RoomReservation;
import airtickets.model.user.User;
import airtickets.repo.hotel.HotelRepository;
import airtickets.repo.hotel.HotelReservationRepository;
import airtickets.repo.hotel.RoomRepository;
import airtickets.repo.hotel.RoomReservationRepository;
import airtickets.repo.user.UserRepository;

@Service
public class RoomReservationService {

	@Autowired
	RoomReservationRepository roomReservationRepository;
	@Autowired
	HotelReservationRepository hotelReservationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	RoomRepository roomRepository;
	
	public List<RoomReservationDTO> getRoomReservations(){
		List<RoomReservationDTO> roomReservationsDTO = new ArrayList<RoomReservationDTO>();
		List<RoomReservation> roomReservations = roomReservationRepository.findAll();
		
		for(RoomReservation r : roomReservations) {
			RoomReservationDTO roomReservationDTO = new RoomReservationDTO(r);
			roomReservationsDTO.add(roomReservationDTO);
		}
		return roomReservationsDTO;
	}

	public List<RoomReservationDTO> getRoomReservationsByUser(Long id) {
		List<RoomReservationDTO> roomReservationsDTO = new ArrayList<RoomReservationDTO>();
		List<RoomReservation> roomReservations = roomReservationRepository.getRoomReservationsByUser(id);
		
		for(RoomReservation r : roomReservations) {
			RoomReservationDTO roomReservationDTO = new RoomReservationDTO(r);
			roomReservationsDTO.add(roomReservationDTO);
		}
		return roomReservationsDTO;
	}

	public List<RoomReservationDTO> getQuickRoomReservationsByCompanyId(long id) {
		List<RoomReservationDTO> roomReservationsDTO = new ArrayList<RoomReservationDTO>();
		List<RoomReservation> roomReservations = roomReservationRepository.getQuickRoomReservationsByCompanyId(id);
		
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
	
	public RoomReservationDTO addQuick(String email, String from, String to, long id, double price) {
		LocalDateTime ldtFrom = LocalDateTime.parse(from);
		LocalDateTime ldtTo = LocalDateTime.parse(to);
		
		User u = userRepository.findByEmail(email);
		Hotel h = hotelRepository.findById(u.getCompany().getId());
		Room r = roomRepository.findById(id);
		
		HotelReservation hr = new HotelReservation();
		hr.setDateFrom(ldtFrom);
		hr.setDateTo(ldtTo);
		hr.setHotel(h);
		hr.setPrice(price);
		hr = hotelReservationRepository.save(hr);
		
		RoomReservation rr = new RoomReservation();
		rr.setHotelReservation(hr);
		rr.setRoom(r);
		
		rr = roomReservationRepository.save(rr);
		
		return new RoomReservationDTO(rr);
	}
}
