package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.FlightDTO;
import airtickets.model.aircompany.Flight;
import airtickets.repo.aircompany.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	public List<FlightDTO> getFlights(){
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		List<Flight> flight = flightRepository.findAll();
		
		for(Flight f : flight) {
			FlightDTO fl = new FlightDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	public FlightDTO getFlight(long id) {
		Flight f = flightRepository.findById(id);
		FlightDTO flight = new FlightDTO(f);
		
		return flight;
	}
	
	public FlightDTO addFlight(FlightDTO flightDTO) {
		Flight flight = new Flight(flightDTO);
		flightRepository.save(flight);
		flightDTO.setId(flight.getId());
		
		return flightDTO;
	}
	
	public void deleteFlight(long id) {
		flightRepository.deleteById(id);
	}
	
}
