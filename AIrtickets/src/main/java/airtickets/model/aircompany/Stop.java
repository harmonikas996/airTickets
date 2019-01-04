package airtickets.model.aircompany;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.aircompany.StopDTO;

@Entity
public class Stop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -37022635195079675L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "airport_id")
	@ManyToOne
	private Airport airport;
	@JoinColumn(name = "flight_id")
	@ManyToOne
	private Flight flight;

	public Stop() {}
	
	public Stop(StopDTO s) {
		id = s.getId();
		airport = new Airport();
		airport.setId(s.getAirportId());
		flight = new Flight();
		flight.setId(s.getFlightId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
