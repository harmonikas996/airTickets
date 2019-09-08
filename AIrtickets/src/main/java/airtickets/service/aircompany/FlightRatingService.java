package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.model.aircompany.FlightRating;
import airtickets.repo.aircompany.FlightRatingRepository;

@Service
public class FlightRatingService {
	
	@Autowired
	FlightRatingRepository flightRatingRepository;
	
	public List<FlightRatingDTO> getFlightRatings(){
		List<FlightRatingDTO> flights = new ArrayList<FlightRatingDTO>();
		List<FlightRating> flight = flightRatingRepository.findAll();
		
		for(FlightRating f : flight) {
			FlightRatingDTO fl = new FlightRatingDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	public FlightRatingDTO getRatingByFlightId(long flightId) {
		FlightRating f = flightRatingRepository.findByflightId(flightId);
		
		if( f == null) {
			return new FlightRatingDTO();
		}
		
		FlightRatingDTO flight = new FlightRatingDTO(f);
		
		return flight;
	}
	
	public FlightRatingDTO getFlightRating(long id) {
		FlightRating f = flightRatingRepository.findById(id);
		FlightRatingDTO flight = new FlightRatingDTO(f);
		
		return flight;
	}
	
	public FlightRatingDTO addFlightRating(FlightRatingDTO flightRatingDTO) {
		FlightRating flight = new FlightRating(flightRatingDTO);
		flightRatingRepository.save(flight);
		flightRatingDTO.setId(flight.getId());
		
		return flightRatingDTO;
	}
	
	public void deleteFlight(long id) {
		flightRatingRepository.deleteById(id);
	}
}
