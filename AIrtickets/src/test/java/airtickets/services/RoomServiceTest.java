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
import airtickets.dto.hotel.RoomDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.repo.aircompany.AircompanyRepository;
import airtickets.service.aircompany.AircompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Mock 
	private AircompanyRepository aircompanyRepoMock;
	
	@Mock
	AircompanyDTO aircompanyDTOMock;
	
	@Mock
	Aircompany aircompanyMock;	

	@InjectMocks
	private AircompanyService aircompanyService;
	
	@Test
	public void getAircompanies() {
		
		// prepare data
		when(aircompanyRepoMock.findAll()).thenReturn(Arrays.asList(aircompanyMock));

		// service method call
		List<AircompanyDTO> returned = aircompanyService.getAircompanies();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(aircompanyRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(aircompanyRepoMock);
	}
	
	@Test
	public void getAircompany() {
		
		// prepare data
		aircompanyMock = new Aircompany();
		aircompanyMock.setId(13);
		when(aircompanyRepoMock.findById(13)).thenReturn(aircompanyMock);

		// service method call
		AircompanyDTO returned = aircompanyService.getAircompany(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(aircompanyRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(aircompanyRepoMock);
	}
	
	@Test
	public void addAircompany() {
		
		// prepare data
		when(aircompanyRepoMock.save(aircompanyMock)).thenReturn(aircompanyMock);

		// service method call
		AircompanyDTO returned = aircompanyService.addAircompany(new AircompanyDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
