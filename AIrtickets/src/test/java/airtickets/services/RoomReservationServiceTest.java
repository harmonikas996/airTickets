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
import airtickets.dto.hotel.RoomReservationDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.HotelReservation;
import airtickets.model.hotel.Room;
import airtickets.model.hotel.RoomReservation;
import airtickets.repo.hotel.RoomReservationRepository;
import airtickets.service.hotel.RoomReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomReservationServiceTest {

	@Mock 
	private RoomReservationRepository roomReservationRepoMock;
	
	@Mock
	RoomDTO roomReservationDTOMock;
	
	@Mock
	HotelReservation hotelReservationMock;
	
	@Mock
	Room roomMock;
	
	@Mock
	RoomReservation roomReservationMock;
	

	@InjectMocks
	private RoomReservationService roomReservationService;
	
	@Test
	public void getRoomReservations() {
		
		// prepare data
		roomReservationMock = new RoomReservation();
		roomReservationMock.setHotelReservation(hotelReservationMock);
		roomReservationMock.setRoom(roomMock);
		when(roomReservationRepoMock.findAll()).thenReturn(Arrays.asList(roomReservationMock));

		// service method call
		List<RoomReservationDTO> returned = roomReservationService.getRoomReservations();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(roomReservationRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(roomReservationRepoMock);
	}
	
	@Test
	public void getRoomReservation() {
		
		// prepare data
		roomReservationMock = new RoomReservation();
		roomReservationMock.setId(13);
		roomReservationMock.setHotelReservation(hotelReservationMock);
		roomReservationMock.setRoom(roomMock);
		when(roomReservationRepoMock.findById(13)).thenReturn(roomReservationMock);

		// service method call
		RoomReservationDTO returned = roomReservationService.getRoomReservation(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(roomReservationRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(roomReservationRepoMock);
	}
	
	@Test
	public void getQuickRoomReservationsByCompanyId() {
		
		// prepare data
		roomReservationMock = new RoomReservation();
		roomReservationMock.setHotelReservation(hotelReservationMock);
		roomReservationMock.setRoom(roomMock);
		when(roomReservationRepoMock.getQuickRoomReservationsByCompanyId(13)).thenReturn(Arrays.asList(roomReservationMock));

		// service method call
		List<RoomReservationDTO> returned = roomReservationService.getQuickRoomReservationsByCompanyId(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasSize(1);		
		verify(roomReservationRepoMock, times(1)).getQuickRoomReservationsByCompanyId(13);
        verifyNoMoreInteractions(roomReservationRepoMock);
	}
	
	@Test
	public void addRoomReservation() {
		
		// prepare data
		roomReservationMock = new RoomReservation();
		roomReservationMock.setHotelReservation(hotelReservationMock);
		roomReservationMock.setRoom(roomMock);
		when(roomReservationRepoMock.save(roomReservationMock)).thenReturn(roomReservationMock);

		// service method call
		RoomReservationDTO returned = roomReservationService.addRoomReservation(new RoomReservationDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}