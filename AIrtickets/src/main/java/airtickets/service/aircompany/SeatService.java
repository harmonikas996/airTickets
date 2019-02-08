package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.FlightReservationDTO;
import airtickets.dto.aircompany.SeatDTO;
import airtickets.model.aircompany.Flight;
import airtickets.model.aircompany.FlightReservation;
import airtickets.model.aircompany.Seat;
import airtickets.model.user.User;
import airtickets.repo.aircompany.FlightReservationRepository;
import airtickets.repo.aircompany.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private FlightReservationRepository flightReservationRepository;

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
	
	public void generateSeats(long id, int n) {
		int j = 1;
		char c = 'A';
		for (int i = 1; i <= n; i++) {
			Seat s = new Seat();
			s.setFlight(new Flight());
			s.getFlight().setId(id);
			if (j == 7) {
				j = 1;
				c = 'A';
			}
			else if (c == 'G') {
				++j;
				c = 'A';
			}
			s.setMark("" + j + c);
			seatRepository.save(s);
			
			++c;
		}
	}

	public List<SeatDTO> getSeatsByFlightId(long id) {
		List<SeatDTO> seats = new ArrayList<>();
		for(Seat s : seatRepository.findByFlightId(id)) {
			SeatDTO seatdto = new SeatDTO(s);
			seats.add(seatdto);
		}
		return seats;
	}

	public FlightReservationDTO reserveSeats(List<SeatDTO> seats) {
		
		FlightReservation fr = new FlightReservation();
		flightReservationRepository.save(fr);
		for (SeatDTO s : seats) {
			Seat st = seatRepository.findById(s.getId());
			st.setClient(new User());
			st.getClient().setId(s.getClientId());
			st.setContact(s.getContact());
			st.setFirstName(s.getFirstName());
			st.setLastName(s.getLastName());
			st.setPassport(s.getPassport());
			st.setReservation(fr);
			seatRepository.save(st);
		}
		
		return new FlightReservationDTO(fr);
	}
}
