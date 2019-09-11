package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.model.aircompany.FlightRating;
import airtickets.repo.aircompany.FlightRatingRepository;

@Service
public class FlightRatingService {
	
	@Autowired
	FlightRatingRepository flightRatingRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<FlightRatingDTO> getFlightRatings(){
		List<FlightRatingDTO> flights = new ArrayList<FlightRatingDTO>();
		List<FlightRating> flight = flightRatingRepository.findAll();
		
		for(FlightRating f : flight) {
			FlightRatingDTO fl = new FlightRatingDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public FlightRatingDTO getRatingByFlightId(long flightId) {
		FlightRating f = flightRatingRepository.findByflightId(flightId);
		
		if( f == null) {
			return new FlightRatingDTO();
		}
		
		FlightRatingDTO flight = new FlightRatingDTO(f);
		
		return flight;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public FlightRatingDTO getFlightRating(long id) {
		FlightRating f = flightRatingRepository.findById(id);
		FlightRatingDTO flight = new FlightRatingDTO(f);
		
		return flight;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public FlightRatingDTO addFlightRating(FlightRatingDTO flightRatingDTO) {
		FlightRating flight = new FlightRating(flightRatingDTO);
		flightRatingRepository.save(flight);
		flightRatingDTO.setId(flight.getId());
		
		return flightRatingDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteFlight(long id) {
		flightRatingRepository.deleteById(id);
	}
}
