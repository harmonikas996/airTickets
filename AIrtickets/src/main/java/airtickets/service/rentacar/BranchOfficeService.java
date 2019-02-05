package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.model.rentacar.BranchOffice;
import airtickets.repo.rentacar.BranchOfficeRepository;

@Service
public class BranchOfficeService {
	@Autowired
	BranchOfficeRepository branchOfficeRepository;

	public List<BranchOfficeDTO> getBranchOffices() {
		List<BranchOfficeDTO> brancOffices = new ArrayList<BranchOfficeDTO>();
		
		for (BranchOffice b  : branchOfficeRepository.findAll()) {
			BranchOfficeDTO brancOffice = new BranchOfficeDTO(b);
			brancOffices.add(brancOffice);
 		}
		return brancOffices;
	}

	public BranchOfficeDTO getBranchOffice(long id) {
		BranchOffice b  = branchOfficeRepository.findById(id);
		BranchOfficeDTO brancOffice = new BranchOfficeDTO(b);
		return brancOffice;
	}
	
	public List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		
		for (String b  : branchOfficeRepository.findAllCities()) {
			locations.add(b);
 		}
		return locations;
	}

	public BranchOfficeDTO addBranchOffice(BranchOfficeDTO brancOfficeDTO) {
		BranchOffice brancOffice = new BranchOffice(brancOfficeDTO);
		branchOfficeRepository.save(brancOffice);
		brancOfficeDTO.setId(brancOffice.getId());
		return brancOfficeDTO;
	}

	public void deleteBranchOffice(long id) {
		branchOfficeRepository.deleteById(id);
	}
}
