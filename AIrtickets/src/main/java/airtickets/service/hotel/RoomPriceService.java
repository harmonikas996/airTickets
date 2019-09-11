package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.RoomPriceDTO;
import airtickets.model.hotel.RoomPrice;
import airtickets.repo.hotel.RoomPriceRepository;
import airtickets.repo.hotel.RoomRepository;

@Service
public class RoomPriceService {
	
	@Autowired
	RoomPriceRepository roomPriceRepository;
	@Autowired
	RoomRepository roomRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomPriceDTO> getRoomPrices(){
		List<RoomPriceDTO> roomPricesDTO = new ArrayList<RoomPriceDTO>();
		List<RoomPrice> roomPrices = roomPriceRepository.findAll();
		
		for(RoomPrice r : roomPrices) {
			RoomPriceDTO roomDTO = new RoomPriceDTO(r);
			roomPricesDTO.add(roomDTO);
		}
		return roomPricesDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RoomPriceDTO> getRoomPricesByHotel(long id){
		List<RoomPriceDTO> roomPricesDTO = new ArrayList<RoomPriceDTO>();
		List<RoomPrice> roomPrices = roomPriceRepository.findByHotel(id);
		
		for(RoomPrice r : roomPrices) {
			RoomPriceDTO roomDTO = new RoomPriceDTO(r);
			roomPricesDTO.add(roomDTO);
		}
		return roomPricesDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public RoomPriceDTO getRoomPrice(long id) {
		RoomPrice r = roomPriceRepository.findById(id);
		RoomPriceDTO roomPriceDTO = new RoomPriceDTO(r);
		
		return roomPriceDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public RoomPriceDTO addRoomPrice(RoomPriceDTO roomPriceDTO) {
		RoomPrice roomPrice = new RoomPrice(roomPriceDTO);
		roomPrice.setRoom(roomRepository.findById(roomPriceDTO.getRoomId()));
		roomPriceRepository.save(roomPrice);
		roomPriceDTO.setId(roomPrice.getId());
		
		return roomPriceDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteRoomPrice(long id) {
		roomPriceRepository.deleteById(id);
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public RoomPriceDTO searchRoomPriceForDateRange(long roomId, String datoFrom, String datoTo) {
		Optional<RoomPrice> r = roomPriceRepository.searchRoomPriceForDateRange(roomId, datoFrom, datoTo);
		
		if(r.isPresent()) {
			
			return new RoomPriceDTO(r.get());
		}
		
		return new RoomPriceDTO();
	}
}
