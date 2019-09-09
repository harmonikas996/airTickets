package airtickets.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import airtickets.dto.aircompany.FlightDTO;
import airtickets.dto.aircompany.SeatDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.aircompany.Airport;
import airtickets.model.aircompany.Flight;
import airtickets.model.aircompany.FlightReservation;
import airtickets.model.aircompany.Seat;
import airtickets.model.user.User;
import airtickets.repo.aircompany.SeatRepository;
import airtickets.service.aircompany.SeatService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatServiceTest {

	@Mock 
	private SeatRepository seatRepoMock;
	
	@Mock
	SeatDTO seatDTOMock;
	
	@Mock
	Seat seatMock;	
	
	@Mock
	User userMock;

	@Mock
	FlightReservation flightReservationMock;

	@Mock
	Flight flightMock;

	@InjectMocks
	private SeatService seatService;
	
	@Test
	public void getSeats() {
		
		// prepare data
		seatMock = new Seat();
		userMock = new User();
		flightReservationMock = new FlightReservation();
		seatMock.setClient(userMock);
		seatMock.setReservation(flightReservationMock);
		seatMock.setFlight(flightMock);
		flightMock.setAirplaneType("AirbusA320");
		when(seatRepoMock.findAll()).thenReturn(Arrays.asList(seatMock));

		// service method call
		List<SeatDTO> returned = seatService.getSeats();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(seatRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(seatRepoMock);
	}
	
	@Test
	public void getSeat() {
		
		// prepare data
		seatMock = new Seat();
		userMock = new User();
		flightReservationMock = new FlightReservation();
		seatMock.setClient(userMock);
		seatMock.setReservation(flightReservationMock);
		seatMock.setFlight(flightMock);
		flightMock.setAirplaneType("AirbusA320");
		seatMock.setId(13);
		when(seatRepoMock.findById(13)).thenReturn(seatMock);

		// service method call
		SeatDTO returned = seatService.getSeat(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(seatRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(seatRepoMock);
	}
	
	@Test
	public void addSeat() {
		
		// prepare data
		seatMock = new Seat();
		userMock = new User();
		userMock.setId(1L);
		flightReservationMock = new FlightReservation();
		seatMock.setClient(userMock);
		seatMock.setReservation(flightReservationMock);
		seatMock.setFlight(flightMock);
		flightMock.setAirplaneType("AirbusA320");
		flightMock.setAirplaneType("AirbusA320");
		
		
		SeatDTO seatDTOMock = new SeatDTO();
		seatDTOMock.setClientId(1L);
		seatDTOMock.setFlightId(1L);
		seatDTOMock.setFlightResId(1L);
		when(seatRepoMock.save(seatMock)).thenReturn(seatMock);

		// service method call
		SeatDTO returned = seatService.addSeat(seatDTOMock);
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
