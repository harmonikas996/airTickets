package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.SeatDTO;
import airtickets.model.aircompany.Seat;
import airtickets.repo.aircompany.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;

	public List<SeatDTO> getSeats(){
		List<SeatDTO> seats = new ArrayList<SeatDTO>();
		List<Seat> seatList = seatRepository.findAll();
		
		for(Seat s : seatList) {
			SeatDTO seatdto = new SeatDTO(s);
			seats.add(seatdto);
		}
		return seats;
	}
	
	public SeatDTO getSeat(long id) {
		Seat s = seatRepository.findById(id);
		SeatDTO seat = new SeatDTO(s);
		
		return seat;
	}
	
	public SeatDTO addSeat(SeatDTO seatDTO) {
		Seat seat = new Seat(seatDTO);
		seatRepository.save(seat);
		seatDTO.setId(seat.getId());
		
		return seatDTO;
	}
	
	public void deleteSeat(long id) {
		seatRepository.deleteById(id);
	}
	
}
