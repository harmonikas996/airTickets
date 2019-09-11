package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.aircompany.AirportDTO;
import airtickets.model.aircompany.Airport;
import airtickets.repo.aircompany.AirportRepository;

@Service
public class AirportService {
	
	@Autowired
	AirportRepository airportRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<AirportDTO> getAirports(){
		List<AirportDTO> airports = new ArrayList<AirportDTO>();
		List<Airport> air = airportRepository.findAll();
		
		for(Airport a : air) {
			AirportDTO aircompany = new AirportDTO(a);
			airports.add(aircompany);
		}
		return airports;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public AirportDTO getAirport(long id) {
		Airport a = airportRepository.findById(id);
		AirportDTO airport = new AirportDTO(a);
		
		return airport;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public AirportDTO addAirport(AirportDTO airportDTO) {
		Airport airport= new Airport(airportDTO);
		airportRepository.save(airport);
		airportDTO.setId(airport.getId());
		
		return airportDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteAircompany(long id) {
		airportRepository.deleteById(id);
	}
	
}
