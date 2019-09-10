package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.Vehicle;
import airtickets.repo.rentacar.BranchOfficeRepository;

@Service
public class BranchOfficeService {
	@Autowired
	BranchOfficeRepository branchOfficeRepository;

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<BranchOfficeDTO> getBranchOffices() {
		List<BranchOfficeDTO> brancOffices = new ArrayList<BranchOfficeDTO>();
		
		for (BranchOffice b  : branchOfficeRepository.findAll()) {
			BranchOfficeDTO brancOffice = new BranchOfficeDTO(b);
			brancOffices.add(brancOffice);
 		}
		return brancOffices;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public BranchOfficeDTO getBranchOffice(long id) {
		BranchOffice b  = branchOfficeRepository.findById(id);
		BranchOfficeDTO brancOffice = new BranchOfficeDTO(b);
		return brancOffice;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		
		for (String b  : branchOfficeRepository.findAllCities()) {
			locations.add(b);
 		}
		return locations;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public BranchOfficeDTO addBranchOffice(BranchOfficeDTO brancOfficeDTO) {
		BranchOffice brancOffice = new BranchOffice(brancOfficeDTO);
		branchOfficeRepository.save(brancOffice);
		brancOfficeDTO.setId(brancOffice.getId());
		return brancOfficeDTO;
	}

	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteBranchOffice(long id) {
		branchOfficeRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<BranchOfficeDTO> findByRentACarId(long id) {
		List<BranchOfficeDTO> brancOffices = new ArrayList<BranchOfficeDTO>();
		
		for (BranchOffice b  : branchOfficeRepository.findByRentACarId(id)) {
			BranchOfficeDTO brancOffice = new BranchOfficeDTO(b);
			brancOffices.add(brancOffice);
 		}
		return brancOffices;
	}
}
