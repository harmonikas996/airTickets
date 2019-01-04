package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.FlightReservationDTO;
import airtickets.model.aircompany.FlightReservation;
import airtickets.model.hotel.HotelReservation;
import airtickets.model.rentacar.CarReservation;
import airtickets.repo.aircompany.FlightReservationRepository;
import airtickets.repo.hotel.HotelReservationRepository;
import airtickets.repo.rentacar.CarReservationRepository;

@Service
public class FlightReservationService {
	
	@Autowired
	FlightReservationRepository flightReservationRepository;
	@Autowired
	CarReservationRepository carReservationRepository;
	@Autowired
	HotelReservationRepository hotelReservationRepository;
	
	public List<FlightReservationDTO> getFlightReservations(){
		List<FlightReservationDTO> flights = new ArrayList<FlightReservationDTO>();
		List<FlightReservation> flight = flightReservationRepository.findAll();
		
		for(FlightReservation f : flight) {
			FlightReservationDTO fl = new FlightReservationDTO(f);
			flights.add(fl);
		}
		return flights;
	}
	
	public FlightReservationDTO getFlightReservation(long id) {
		FlightReservation f = flightReservationRepository.findById(id);
		FlightReservationDTO flight = new FlightReservationDTO(f);
		
		return flight;
	}
	
	public FlightReservationDTO addFlightReservation(FlightReservationDTO flightResDTO) {
		FlightReservation flight = new FlightReservation(flightResDTO);
		
		CarReservation carRes = carReservationRepository.findById(flightResDTO.getCarResId());
		flight.setCarReservation(carRes);
		
		HotelReservation hotelRes = hotelReservationRepository.findById(flightResDTO.getHotelResId());
		flight.setHotelReservation(hotelRes);
		
		flightReservationRepository.save(flight);
		flightResDTO.setId(flight.getId());
		
		return flightResDTO;
	}
	
	public void deleteFlightReservation(long id) {
		flightReservationRepository.deleteById(id);
	}

}




