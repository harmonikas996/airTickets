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

import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.RentACar;
import airtickets.model.rentacar.Vehicle;
import airtickets.model.user.User;
import airtickets.repo.rentacar.CarRatingRepository;
import airtickets.service.rentacar.CarRatingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRatingServiceTest {
	
	@Mock 
	private CarRatingRepository carRatingRepoMock;
	
	@Mock
	CarRatingDTO carRatingDTOMock;
	
	@Mock
	CarRating carRatingMock;
	
	@Mock
	Vehicle vehicleMock;
	
	@Mock
	User userMock;
	

	@InjectMocks
	private CarRatingService carRatingService;
	
	@Test
	public void getCarRatings() {
		
		// prepare data
		carRatingMock = new CarRating();
		vehicleMock = new Vehicle();
		userMock = new User();
		carRatingMock.setVehicle(vehicleMock);
		carRatingMock.setUser(userMock);
		when(carRatingRepoMock.findAll()).thenReturn(Arrays.asList(carRatingMock));

		// service method call
		List<CarRatingDTO> returned = carRatingService.getCarRatings();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(carRatingRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(carRatingRepoMock);
	}
	
	@Test
	public void getRatingByVehicleId() {
		
		// prepare data
		carRatingMock = new CarRating();
		vehicleMock = new Vehicle();
		userMock = new User();
		carRatingMock.setId(13);
		carRatingMock.setVehicle(vehicleMock);
		carRatingMock.setUser(userMock);
		when(carRatingRepoMock.findByVehicleId(13)).thenReturn(carRatingMock);

		// service method call
		CarRatingDTO returned = carRatingService.getRatingByVehicleId(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(carRatingRepoMock, times(1)).findByVehicleId(13);
        verifyNoMoreInteractions(carRatingRepoMock);
	}
	
	@Test
	public void getCarRating() {
		
		// prepare data
		carRatingMock = new CarRating();
		vehicleMock = new Vehicle();
		userMock = new User();
		carRatingMock.setId(13);
		carRatingMock.setVehicle(vehicleMock);
		carRatingMock.setUser(userMock);
		when(carRatingRepoMock.findById(13)).thenReturn(carRatingMock);

		// service method call
		CarRatingDTO returned = carRatingService.getCarRating(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(carRatingRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(carRatingRepoMock);
	}
	
	@Test
	public void addCarRating() {
		
		// prepare data
		carRatingDTOMock = new CarRatingDTO();
		when(carRatingRepoMock.save(carRatingMock)).thenReturn(carRatingMock);

		// service method call
		CarRatingDTO returned = carRatingService.addCarRating(carRatingDTOMock);
		
		// check data retrieval
		assertThat(returned).isNotNull();
		assertThat(returned).hasNoNullFieldsOrProperties();
	}
	
	@Test
	public void getRating() {
		
		// prepare data
		vehicleMock = new Vehicle();
		userMock = new User();
		carRatingMock = new CarRating();
		carRatingMock.setRating(5);
		carRatingMock.setVehicle(vehicleMock);
		carRatingMock.setUser(userMock);
		when(carRatingRepoMock.findAllById(11L)).thenReturn(Arrays.asList(carRatingMock));

		// service method call
		double returned = carRatingService.getRating(11L);
		
		// check data retrieval
		assertThat(returned).isNotNull();
		assertThat(returned).isEqualByComparingTo(5.0);
		verify(carRatingRepoMock, times(1)).findAllById(11L);
        verifyNoMoreInteractions(carRatingRepoMock);
	}
}
