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

import airtickets.dto.hotel.RoomDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.repo.hotel.RoomRepository;
import airtickets.service.hotel.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Mock 
	private RoomRepository roomRepoMock;
	
	@Mock
	RoomDTO roomDTOMock;
	
	@Mock
	Hotel hotelMock;
	
	@Mock
	Room roomMock;
	

	@InjectMocks
	private RoomService roomService;
	
	@Test
	public void getRooms() {
		
		// prepare data
		roomMock = new Room();
		hotelMock = new Hotel();
		roomMock.setHotel(hotelMock);
		when(roomRepoMock.findAll()).thenReturn(Arrays.asList(roomMock));

		// service method call
		List<RoomDTO> returned = roomService.getRooms();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(roomRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(roomRepoMock);
	}
	
	@Test
	public void getRoom() {
		
		// prepare data
		roomMock = new Room();
		hotelMock = new Hotel();
		roomMock.setId(13);
		roomMock.setHotel(hotelMock);
		when(roomRepoMock.findById(13)).thenReturn(roomMock);

		// service method call
		RoomDTO returned = roomService.getRoom(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(roomRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(roomRepoMock);
	}
	
	@Test
	public void getRoomsByHotel() {
		
		// prepare data
		roomMock = new Room();
		hotelMock = new Hotel();
		roomMock.setId(13);
		roomMock.setHotel(hotelMock);
		when(roomRepoMock.getRoomsByHotel(13)).thenReturn(Arrays.asList(roomMock));

		// service method call
		List<RoomDTO> returned = roomService.getRoomsByHotel(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasSize(1);		
		verify(roomRepoMock, times(1)).getRoomsByHotel(13);
        verifyNoMoreInteractions(roomRepoMock);
	}
	
	@Test
	public void addRoom() {
		
		// prepare data
		roomMock = new Room();
		hotelMock = new Hotel();
		roomMock.setHotel(hotelMock);
		when(roomRepoMock.save(roomMock)).thenReturn(roomMock);

		// service method call
		RoomDTO returned = roomService.addRoom(new RoomDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
