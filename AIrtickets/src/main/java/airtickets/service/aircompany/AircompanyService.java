package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.repo.aircompany.AircompanyRepository;

@Service
public class AircompanyService {
	
	@Autowired
	AircompanyRepository airCompanyRepository;
	
	public List<AircompanyDTO> getAircompanies(){
		List<AircompanyDTO> aircompanies = new ArrayList<AircompanyDTO>();
		List<Aircompany> air = airCompanyRepository.findAll();
		
		for(Aircompany a : air) {
			AircompanyDTO aircompany = new AircompanyDTO(a);
			aircompanies.add(aircompany);
		}
		return aircompanies;
	}
	
	public AircompanyDTO getAircompany(long id) {
		Aircompany a = airCompanyRepository.findById(id);
		AircompanyDTO aircompany = new AircompanyDTO(a);
		
		return aircompany;
	}
	
	public AircompanyDTO addAircompany(AircompanyDTO aircompanyDTO) {
		Aircompany aircompany = new Aircompany(aircompanyDTO);
		airCompanyRepository.save(aircompany);
		aircompanyDTO.setId(aircompany.getId());
		
		return aircompanyDTO;
	}
	
	public void deleteAircompany(long id) {
		airCompanyRepository.deleteById(id);
	}
}
