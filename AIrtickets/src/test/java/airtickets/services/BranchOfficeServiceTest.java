package airtickets.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
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
import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.RentACar;
import airtickets.repo.rentacar.BranchOfficeRepository;
import airtickets.service.rentacar.BranchOfficeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BranchOfficeServiceTest {
	
	@Mock 
	private BranchOfficeRepository branchOfficeRepoMock;
	
	@Mock
	BranchOfficeDTO branchOfficeDTOMock;
	
	@Mock
	BranchOffice branchOfficeMock;
	
	@Mock
	RentACar rentacarMock;
	

	@InjectMocks
	private BranchOfficeService branchOfficeService;
	
	@Test
	public void getBranchOffices() {
		
		// prepare data
		branchOfficeMock = new BranchOffice();
		rentacarMock = new RentACar();
		branchOfficeMock.setRentACar(rentacarMock);
		when(branchOfficeRepoMock.findAll()).thenReturn(Arrays.asList(branchOfficeMock));

		// service method call
		List<BranchOfficeDTO> returnedBranchOfficies = branchOfficeService.getBranchOffices();
		
		// check data retrieval
		assertThat(returnedBranchOfficies).hasSize(1);		
		verify(branchOfficeRepoMock, times(1)).findAll();
        verifyNoMoreInteractions(branchOfficeRepoMock);
	}
	
	@Test
	public void getBranchOffice() {
		
		// prepare data
		branchOfficeMock = new BranchOffice();
		rentacarMock = new RentACar();
		branchOfficeMock.setId(13);
		branchOfficeMock.setRentACar(rentacarMock);
		when(branchOfficeRepoMock.findById(13)).thenReturn(branchOfficeMock);

		// service method call
		BranchOfficeDTO returnedBranchOffice = branchOfficeService.getBranchOffice(13);
		
		// check data retrieval
		assertThat(returnedBranchOffice).isNotNull();		
		assertThat(returnedBranchOffice).hasFieldOrPropertyWithValue("id", new Long(13));		
		verify(branchOfficeRepoMock, times(1)).findById(13);
        verifyNoMoreInteractions(branchOfficeRepoMock);
	}
	
	@Test
	public void getLocations() {
		
		// prepare data
		when(branchOfficeRepoMock.findAllCities()).thenReturn(Arrays.asList(new String()));

		// service method call
		List<String> returnedLocations = branchOfficeService.getLocations();
		
		// check data retrieval
		assertThat(returnedLocations).isNotEmpty();
		assertThat(returnedLocations).hasSize(1);		
		verify(branchOfficeRepoMock, times(1)).findAllCities();
        verifyNoMoreInteractions(branchOfficeRepoMock);
	}
	
	@Test
	public void addBranchOffice() {
		
		// prepare data
		branchOfficeDTOMock = new BranchOfficeDTO();
		branchOfficeMock = new BranchOffice(branchOfficeDTOMock);
		when(branchOfficeRepoMock.save(branchOfficeMock)).thenReturn(branchOfficeMock);

		// service method call
		BranchOfficeDTO returnedBranchOfficeDTO = branchOfficeService.addBranchOffice(branchOfficeDTOMock);
		
		// check data retrieval
		assertThat(returnedBranchOfficeDTO).isNotNull();
	}
	
	@Test
	public void findByRentACarId() {
		
		// prepare data
		branchOfficeDTOMock = new BranchOfficeDTO();
		branchOfficeDTOMock.setRentACarId(11);
		branchOfficeMock = new BranchOffice(branchOfficeDTOMock);
		when(branchOfficeRepoMock.findByRentACarId(11)).thenReturn(Arrays.asList(branchOfficeMock));

		// service method call
		List<BranchOfficeDTO> returnedBranchOfficeDTOs = branchOfficeService.findByRentACarId(11);
		
		// check data retrieval
		assertThat(returnedBranchOfficeDTOs).isNotEmpty();
		assertThat(returnedBranchOfficeDTOs).hasSize(1);		
		verify(branchOfficeRepoMock, times(1)).findByRentACarId(11);
        verifyNoMoreInteractions(branchOfficeRepoMock);
	}
}
