package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<RoomPriceDTO> getRoomPrices(){
		List<RoomPriceDTO> roomPricesDTO = new ArrayList<RoomPriceDTO>();
		List<RoomPrice> roomPrices = roomPriceRepository.findAll();
		
		for(RoomPrice r : roomPrices) {
			RoomPriceDTO roomDTO = new RoomPriceDTO(r);
			roomPricesDTO.add(roomDTO);
		}
		return roomPricesDTO;
	}
	
	public RoomPriceDTO getRoomPrice(long id) {
		RoomPrice r = roomPriceRepository.findById(id);
		RoomPriceDTO roomPriceDTO = new RoomPriceDTO(r);
		
		return roomPriceDTO;
	}
	
	public RoomPriceDTO addRoomPrice(RoomPriceDTO roomPriceDTO) {
		RoomPrice roomPrice = new RoomPrice(roomPriceDTO);
		roomPrice.setRoom(roomRepository.findById(roomPriceDTO.getRoomId()));
		roomPriceRepository.save(roomPrice);
		roomPriceDTO.setId(roomPrice.getId());
		
		return roomPriceDTO;
	}
	
	public void deleteRoomPrice(long id) {
		roomPriceRepository.deleteById(id);
	}
}
