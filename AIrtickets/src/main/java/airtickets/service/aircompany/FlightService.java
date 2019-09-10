package airtickets.service.aircompany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.dto.aircompany.FlightDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.aircompany.Flight;
import airtickets.model.rentacar.RentACar;
import airtickets.repo.aircompany.FlightRepository;

@Service
public class FlightService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FlightRepository flightRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public FlightDTO getFlightAdmin(long id) {
		Flight f = flightRepository.findById(id);
		FlightDTO flightDTO = new FlightDTO(f);
		
		return flightDTO;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<FlightDTO> getFlightsByUser(Long id) {
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		List<Flight> flight = flightRepository.getFlightsByUser(id);
		
		for(Flight f : flight) {
			FlightDTO fl = new FlightDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<FlightDTO> getFlights(){
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		List<Flight> flight = flightRepository.findAll();
		
		for(Flight f : flight) {
			FlightDTO fl = new FlightDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public FlightDTO getFlight(long id) {
		Flight f = flightRepository.findById(id);
		FlightDTO flight = new FlightDTO(f);
		
		return flight;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public FlightDTO addFlight(FlightDTO flightDTO) {
		Flight flight = new Flight(flightDTO);
		flightRepository.save(flight);
		flightDTO.setId(flight.getId());
		
		return flightDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteFlight(long id) {
		flightRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<FlightDTO> searchFlights(String placeFromId, String placeToId, String date) {
		
		LocalDateTime dt = LocalDateTime.parse(date);
		
		String pf = "^[0-9]+$";
		String pt = "^[0-9]+$";
		
		if(!placeFromId.equals("-1"))
			pf = "^" + placeFromId + "$";
		if(!placeToId.equals("-1"))
			pt = "^" + placeToId + "$";
		
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		
		date = "^" + dt.toString().substring(0, 10);
		
		for (Flight f  : flightRepository.searchFlights(pf, pt, date)) {
			FlightDTO flight = new FlightDTO(f);
			flights.add(flight);
 		}
		return flights;
	}

	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<FlightDTO> searchFlightsByCompany(String placeFromId, String placeToId, String date, Long companyId) {
		
		LocalDateTime dt = LocalDateTime.parse(date);
		
		String pf = "^[0-9]+$";
		String pt = "^[0-9]+$";
		
		if(!placeFromId.equals("-1"))
			pf = "^" + placeFromId + "$";
		if(!placeToId.equals("-1"))
			pt = "^" + placeToId + "$";
		
		List<FlightDTO> flights = new ArrayList<FlightDTO>();
		
		date = "^" + dt.toString().substring(0, 10);
		
		for (Flight f  : flightRepository.searchFlightsByCompany(pf, pt, date, companyId)) {
			FlightDTO flight = new FlightDTO(f);
			flights.add(flight);
 		}
		return flights;
	}
	
	
	
}
