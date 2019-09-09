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
import airtickets.dto.hotel.RoomRatingDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.model.hotel.RoomRating;
import airtickets.model.user.User;
import airtickets.repo.hotel.RoomRatingRepository;
import airtickets.service.hotel.RoomRatingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRatingServiceTest {

	@Mock 
	private RoomRatingRepository roomRatingRepoMock;
	
	@Mock
	RoomRatingDTO roomRatingDTOMock;
	
	@Mock
	Hotel hotelMock;
	
	@Mock
	User userMock;
	
	@Mock
	RoomRating roomRatingMock;
	
	@Mock
	Room roomMock;
	

	@InjectMocks
	private RoomRatingService roomRatingService;
	
	@Test
	public void getRoomRatings() {
		
		// prepare data
		roomRatingMock = new RoomRating();
		roomRatingMock.setRoom(roomMock);
		roomRatingMock.setUser(userMock);
		when(roomRatingRepoMock.findAll()).thenReturn(Arrays.asList(roomRatingMock));

		// service method call
		List<RoomRatingDTO> returned = roomRatingService.getRoomRatings();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(roomRatingRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(roomRatingRepoMock);
	}
	
	@Test
	public void getRoomRating() {
		
		// prepare data
		roomRatingMock = new RoomRating();
		roomRatingMock.setId(13);
		roomRatingMock.setRoom(roomMock);
		roomRatingMock.setUser(userMock);
		when(roomRatingRepoMock.findById(13)).thenReturn(roomRatingMock);

		// service method call
		RoomRatingDTO returned = roomRatingService.getRoomRating(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(roomRatingRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(roomRatingRepoMock);
	}
	
	@Test
	public void getRatingByRoomId() {
		
		// prepare data
		roomRatingMock = new RoomRating();
		roomRatingMock.setRoom(roomMock);
		roomRatingMock.setUser(userMock);
		when(roomRatingRepoMock.findByroomId(13)).thenReturn(roomRatingMock);

		// service method call
		RoomRatingDTO returned = roomRatingService.getRatingByRoomId(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		verify(roomRatingRepoMock, times(1)).findByroomId(13);
        verifyNoMoreInteractions(roomRatingRepoMock);
	}
	
	@Test
	public void addRoomRating() {
		
		// prepare data
		roomRatingMock = new RoomRating();
		roomRatingMock.setRoom(roomMock);
		roomRatingMock.setUser(userMock);
		when(roomRatingRepoMock.save(roomRatingMock)).thenReturn(roomRatingMock);

		// service method call
		RoomRatingDTO returned = roomRatingService.addRoomRating(new RoomRatingDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
