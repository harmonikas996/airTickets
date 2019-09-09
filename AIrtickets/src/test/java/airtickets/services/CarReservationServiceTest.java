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

import airtickets.dto.rentacar.CarReservationDTO;
import airtickets.model.rentacar.CarReservation;
import airtickets.model.rentacar.RentACar;
import airtickets.model.rentacar.Vehicle;
import airtickets.repo.rentacar.CarReservationRepository;
import airtickets.service.rentacar.CarReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarReservationServiceTest {

	@Mock 
	private CarReservationRepository carReservationRepoMock;
	
	@Mock
	CarReservationDTO carReservationDTOMock;
	
	@Mock
	CarReservation carReservationMock;
	
	@Mock
	Vehicle vehicleMock;
	
	@Mock
	RentACar rentACarMock;
	

	@InjectMocks
	private CarReservationService carReservationService;
	
	@Test
	public void getCarReservations() {
		
		// prepare data
		carReservationMock = new CarReservation();
		vehicleMock = new Vehicle();
		carReservationMock.setVehicle(vehicleMock);
		when(carReservationRepoMock.findAll()).thenReturn(Arrays.asList(carReservationMock));

		// service method call
		List<CarReservationDTO> returned = carReservationService.getCarReservations();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(carReservationRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(carReservationRepoMock);
	}
	
	@Test
	public void getCarReservationsByUser() {
		
		// prepare data
		carReservationMock = new CarReservation();
		vehicleMock = new Vehicle();
		carReservationMock.setVehicle(vehicleMock);
		when(carReservationRepoMock.getCarReservationsByUser(11L)).thenReturn(Arrays.asList(carReservationMock));

		// service method call
		List<CarReservationDTO> returned = carReservationService.getCarReservationsByUser(11L);
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(carReservationRepoMock, times(1)).getCarReservationsByUser(11L);
        verifyNoMoreInteractions(carReservationRepoMock);
	}
	
	@Test
	public void getQuickCarReservationsByCompanyId() {
		
		// prepare data
		carReservationMock = new CarReservation();
		vehicleMock = new Vehicle();
		rentACarMock = new RentACar();
		rentACarMock.setId(11L);
		vehicleMock.setRentACar(rentACarMock);
		carReservationMock.setVehicle(vehicleMock);
		when(carReservationRepoMock.getQuickCarReservationsByCompanyId(11L)).thenReturn(Arrays.asList(carReservationMock));

		// service method call
		List<CarReservationDTO> returned = carReservationService.getQuickCarReservationsByCompanyId(11L);
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(carReservationRepoMock, times(1)).getQuickCarReservationsByCompanyId(11L);
        verifyNoMoreInteractions(carReservationRepoMock);
	}
	
	@Test
	public void getCarReservation() {
		
		// prepare data
		carReservationMock = new CarReservation();
		vehicleMock = new Vehicle();
		rentACarMock = new RentACar();
//		rentACarMock.setId(11L);
		vehicleMock.setRentACar(rentACarMock);
		carReservationMock.setVehicle(vehicleMock);
		carReservationMock.setId(11L);
		when(carReservationRepoMock.findById(11L)).thenReturn(carReservationMock);

		// service method call
		CarReservationDTO returned = carReservationService.getCarReservation(11L);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		verify(carReservationRepoMock, times(1)).findById(11L);
        verifyNoMoreInteractions(carReservationRepoMock);
	}
	
	@Test
	public void addCarReservation() {
		
		// prepare data
		carReservationMock = new CarReservation();
		vehicleMock = new Vehicle();
		rentACarMock = new RentACar();
		vehicleMock.setRentACar(rentACarMock);
		carReservationMock.setVehicle(vehicleMock);
		when(carReservationRepoMock.save(carReservationMock)).thenReturn(carReservationMock);

		// service method call
		CarReservationDTO returned = carReservationService.addCarReservation(carReservationDTOMock);
		
		// check data retrieval
		assertThat(returned).isNotNull();
	}
}
