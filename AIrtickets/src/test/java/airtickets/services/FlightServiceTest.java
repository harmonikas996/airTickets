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

import airtickets.dto.aircompany.AirportDTO;
import airtickets.dto.aircompany.FlightDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.aircompany.AirplaneType;
import airtickets.model.aircompany.Airport;
import airtickets.model.aircompany.Flight;
import airtickets.repo.aircompany.FlightRepository;
import airtickets.service.aircompany.FlightService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

	@Mock 
	private FlightRepository flightRepoMock;
	
	@Mock
	FlightDTO flightDTOMock;
	
	@Mock
	Flight flightMock;	
	
	@Mock
	Aircompany aircompanyMock;

	@Mock
	Airport airportMock;

	@InjectMocks
	private FlightService flightService;
	
	@Test
	public void getFlights() {
		
		// prepare data
		flightMock = new Flight();
		aircompanyMock = new Aircompany();
		airportMock = new Airport();
		flightMock.setCompany(aircompanyMock);
		flightMock.setPlaceFrom(airportMock);
		flightMock.setPlaceTo(airportMock);
		flightMock.setAirplaneType("AirbusA320");
		when(flightRepoMock.findAll()).thenReturn(Arrays.asList(flightMock));

		// service method call
		List<FlightDTO> returned = flightService.getFlights();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(flightRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(flightRepoMock);
	}
	
	@Test
	public void getFlight() {
		
		// prepare data
		flightMock = new Flight();
		aircompanyMock = new Aircompany();
		airportMock = new Airport();
		flightMock.setCompany(aircompanyMock);
		flightMock.setPlaceFrom(airportMock);
		flightMock.setPlaceTo(airportMock);
		flightMock.setId(13);
		flightMock.setAirplaneType("AirbusA320");
		when(flightRepoMock.findById(13)).thenReturn(flightMock);

		// service method call
		FlightDTO returned = flightService.getFlight(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(flightRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(flightRepoMock);
	}
	
	@Test
	public void addFlight() {
		
		// prepare data
		flightMock = new Flight();
		aircompanyMock = new Aircompany();
		airportMock = new Airport();
		flightMock.setCompany(aircompanyMock);
		flightMock.setPlaceFrom(airportMock);
		flightMock.setPlaceTo(airportMock);
		flightMock.setAirplaneType("AirbusA320");
		FlightDTO flightDTOMock = new FlightDTO();
		flightDTOMock.setAirplaneType("AirbusA320");
		when(flightRepoMock.save(flightMock)).thenReturn(flightMock);

		// service method call
		FlightDTO returned = flightService.addFlight(flightDTOMock);
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
