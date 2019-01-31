package airtickets.controller.rentacar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.service.rentacar.BranchOfficeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/branchoffices")
public class BranchOfficeController {
	
	@Autowired
	private BranchOfficeService branchOfficeService;

	@GetMapping("/all")
	public List<BranchOfficeDTO> getAllBranchOffices() {
		return branchOfficeService.getBranchOffices();
	}

	@GetMapping("/{id}")
	public BranchOfficeDTO getById(@PathVariable Long id) {
		return branchOfficeService.getBranchOffice(id);
	}

	@PostMapping("/new")
    public BranchOfficeDTO addBranchOffice(@RequestBody BranchOfficeDTO branchOfficeDTO) {
			return branchOfficeService.addBranchOffice(branchOfficeDTO);
	}

	@PutMapping("{id}/update")
	public BranchOfficeDTO updateBranchOffice(@RequestBody BranchOfficeDTO branchOfficeDTO, @PathVariable Long id) {
		branchOfficeDTO.setId(id);
		return branchOfficeService.addBranchOffice(branchOfficeDTO);
	}

	@DeleteMapping("{id}/delete")
	public void deleteBranchOffice(@PathVariable Long id) {
		branchOfficeService.deleteBranchOffice(id);
	}
	
}
