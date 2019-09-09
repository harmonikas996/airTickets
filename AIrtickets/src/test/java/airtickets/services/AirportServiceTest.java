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

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.dto.aircompany.AirportDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.aircompany.Airport;
import airtickets.repo.aircompany.AirportRepository;
import airtickets.service.aircompany.AirportService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

	@Mock 
	private AirportRepository airportRepoMock;
	
	@Mock
	AirportDTO aircompanyDTOMock;
	
	@Mock
	Airport aircompanyMock;	

	@InjectMocks
	private AirportService aircompanyService;
	
	@Test
	public void getAirports() {
		
		// prepare data
		when(airportRepoMock.findAll()).thenReturn(Arrays.asList(aircompanyMock));

		// service method call
		List<AirportDTO> returned = aircompanyService.getAirports();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(airportRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(airportRepoMock);
	}
	
	@Test
	public void getAirport() {
		
		// prepare data
		aircompanyMock = new Airport();
		aircompanyMock.setId(13);
		when(airportRepoMock.findById(13)).thenReturn(aircompanyMock);

		// service method call
		AirportDTO returned = aircompanyService.getAirport(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(airportRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(airportRepoMock);
	}
	
	@Test
	public void addAirport() {
		
		// prepare data
		when(airportRepoMock.save(aircompanyMock)).thenReturn(aircompanyMock);

		// service method call
		AirportDTO returned = aircompanyService.addAirport(new AirportDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
