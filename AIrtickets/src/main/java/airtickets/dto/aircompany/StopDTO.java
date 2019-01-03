package airtickets.dto.aircompany;

import airtickets.model.aircompany.Stop;

public class StopDTO {
	
	private long id;
	private long airportId;
	private long flightId;
	
	public StopDTO() {}
	
	public StopDTO(Stop s) {
		id = s.getId();
		airportId = s.getAirport().getId();
		flightId = s.getFlight().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAirportId() {
		return airportId;
	}

	public void setAirportId(long airportId) {
		this.airportId = airportId;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	
}
