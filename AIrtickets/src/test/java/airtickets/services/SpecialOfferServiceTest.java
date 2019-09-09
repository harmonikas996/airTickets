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
import airtickets.dto.hotel.SpecialOfferDTO;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.Room;
import airtickets.model.hotel.SpecialOffer;
import airtickets.repo.hotel.SpecialOfferRepository;
import airtickets.service.hotel.SpecialOfferService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialOfferServiceTest {

	@Mock 
	private SpecialOfferRepository specialOfferRepoMock;
	
	@Mock
	SpecialOfferDTO specialOfferDTOMock;
	
	@Mock
	SpecialOffer specialOfferMock;
	

	@InjectMocks
	private SpecialOfferService specialOfferService;
	
	@Test
	public void getSpecialOffers() {
		
		// prepare data
		when(specialOfferRepoMock.findAll()).thenReturn(Arrays.asList(specialOfferMock));

		// service method call
		List<SpecialOfferDTO> returned = specialOfferService.getSpecialOffers();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(specialOfferRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(specialOfferRepoMock);
	}
	
	@Test
	public void getSpecialOffer() {
		
		// prepare data
		specialOfferMock = new SpecialOffer();
		specialOfferMock.setId(13);
		when(specialOfferRepoMock.findById(13)).thenReturn(specialOfferMock);

		// service method call
		SpecialOfferDTO returned = specialOfferService.getSpecialOffer(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(specialOfferRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(specialOfferRepoMock);
	}
	
	@Test
	public void addSpecialOffer() {
		
		// prepare data
		when(specialOfferRepoMock.save(specialOfferMock)).thenReturn(specialOfferMock);

		// service method call
		SpecialOfferDTO returned = specialOfferService.addSpecialOffer(new SpecialOfferDTO());
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
