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

import airtickets.dto.hotel.HotelDTO;
import airtickets.dto.hotel.RoomDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.repo.hotel.HotelRepository;
import airtickets.service.hotel.HotelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

	@Mock 
	private HotelRepository hotelRepoMock;
	
	@Mock
	Hotel hotelMock;
	
	@Mock
	HotelDTO hotelDTOMock;

	@InjectMocks
	private HotelService hotelService;
	
	@Test
	public void getHotels() {
		
		// prepare data
		hotelMock = new Hotel();
		when(hotelRepoMock.findAll()).thenReturn(Arrays.asList(hotelMock));

		// service method call
		List<HotelDTO> returned = hotelService.getHotels();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(hotelRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(hotelRepoMock);
	}
	
	@Test
	public void getHotel() {
		
		// prepare data
		hotelMock = new Hotel();
		hotelMock.setId(13);
		when(hotelRepoMock.findById(13)).thenReturn(hotelMock);

		// service method call
		HotelDTO returned = hotelService.getHotel(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(hotelRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(hotelRepoMock);
	}
	
	@Test
	public void addHotel() {
		
		// prepare data
		hotelMock = new Hotel();
		when(hotelRepoMock.save(hotelMock)).thenReturn(hotelMock);

		// service method call
		HotelDTO returned = hotelService.addHotel(new HotelDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}

