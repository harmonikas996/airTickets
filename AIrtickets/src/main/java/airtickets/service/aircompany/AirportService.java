package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.AirportDTO;
import airtickets.model.aircompany.Airport;
import airtickets.repo.aircompany.AirportRepository;

@Service
public class AirportService {
	
	@Autowired
	AirportRepository airportRepository;
	
	public List<AirportDTO> getAirports(){
		List<AirportDTO> airports = new ArrayList<AirportDTO>();
		List<Airport> air = airportRepository.findAll();
		
		for(Airport a : air) {
			AirportDTO aircompany = new AirportDTO(a);
			airports.add(aircompany);
		}
		return airports;
	}
	
	public AirportDTO getAirport(long id) {
		Airport a = airportRepository.findById(id);
		AirportDTO airport = new AirportDTO(a);
		
		return airport;
	}
	
	public AirportDTO addAirport(AirportDTO airportDTO) {
		Airport airport= new Airport(airportDTO);
		airportRepository.save(airport);
		airportDTO.setId(airport.getId());
		
		return airportDTO;
	}
	
	public void deleteAircompany(long id) {
		airportRepository.deleteById(id);
	}
	
}
