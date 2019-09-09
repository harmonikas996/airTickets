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

import airtickets.dto.aircompany.FlightReservationDTO;
import airtickets.model.aircompany.Flight;
import airtickets.model.aircompany.FlightReservation;
import airtickets.model.hotel.Hotel;
import airtickets.model.hotel.HotelReservation;
import airtickets.model.rentacar.CarReservation;
import airtickets.model.rentacar.RentACar;
import airtickets.model.rentacar.Vehicle;
import airtickets.repo.aircompany.FlightReservationRepository;
import airtickets.repo.hotel.HotelReservationRepository;
import airtickets.repo.rentacar.CarReservationRepository;
import airtickets.service.aircompany.FlightReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightReservationServiceTest {

	@Mock 
	private FlightReservationRepository flightReservationRepoMock;
	
	@Mock 
	private CarReservationRepository carReservationRepoMock;
	
	@Mock 
	private HotelReservationRepository hotelReservationRepoMock;
	
	@Mock
	FlightReservationDTO flightReservationDTOMock;
	
	@Mock
	FlightReservation flightReservationMock;
	
	@Mock
	HotelReservation hotelReservationMock;
	
	@Mock
	CarReservation carReservationMock;
	
	@Mock
	Flight flightMock;
	
	@Mock
	Hotel hotelMock;
	
	@Mock
	Vehicle vehicleMock;
	
	@Mock
	RentACar rentACarMock;

	@InjectMocks
	private FlightReservationService flightReservationService;
	
	@Test
	public void getFlightReservations() {
		
		// prepare data
		when(flightReservationRepoMock.findAll()).thenReturn(Arrays.asList(flightReservationMock));

		// service method call
		List<FlightReservationDTO> returned = flightReservationService.getFlightReservations();
		
		// check data retrieval
		assertThat(returned).hasSize(1);		
		verify(flightReservationRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(flightReservationRepoMock);
	}
	
	@Test
	public void getFlightReservation() {
		
		// prepare data
		flightReservationMock = new FlightReservation();
		flightReservationMock.setId(13);
		flightReservationMock.setCarReservation(carReservationMock);
		flightReservationMock.setHotelReservation(hotelReservationMock);
		flightReservationMock.setFlight(flightMock);
		when(flightReservationRepoMock.findById(13)).thenReturn(flightReservationMock);

		// service method call
		FlightReservationDTO returned = flightReservationService.getFlightReservation(13);
		
		// check data retrieval
		assertThat(returned).isNotNull();		
		assertThat(returned).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(flightReservationRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(flightReservationRepoMock);
	}
	
//	@Test
//	public void addFlightReservation() {
//		
//		// prepare data
//		FlightReservationDTO flightReservationDTOMock = new FlightReservationDTO();
//		carReservationMock = new CarReservation();
//		rentACarMock = new RentACar();
//		vehicleMock = new Vehicle();
//		vehicleMock.setRentACar(rentACarMock);
//		carReservationMock.setId(1L);
//		carReservationMock.setVehicle(vehicleMock);
//		hotelReservationMock = new HotelReservation();
//		hotelMock = new Hotel();
//		hotelReservationMock.setId(2L);
//		hotelReservationMock.setHotel(hotelMock);
//		flightReservationDTOMock.setCarResId(1L);
//		flightReservationDTOMock.setFlightId(2L);
//		flightReservationDTOMock.setHotelResId(3L);
//		when(carReservationRepoMock.findById(1L)).thenReturn(carReservationMock);
//		when(hotelReservationRepoMock.findById(2L)).thenReturn(hotelReservationMock);
//		when(flightReservationRepoMock.save(flightReservationMock)).thenReturn(flightReservationMock);
//
//		// service method call
//		FlightReservationDTO returned = flightReservationService.addFlightReservation(flightReservationDTOMock);
//		
//		// check data retrieval
//		assertThat(returned).isNotNull();
//	}
}
