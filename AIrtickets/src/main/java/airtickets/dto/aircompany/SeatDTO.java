package airtickets.dto.aircompany;

import airtickets.model.aircompany.Seat;

public class SeatDTO {
	
	private long id;
	private long clientId;
	private String passport;
	private long flightId;
	private long flightResId;
	
	public SeatDTO() {}
	
	public SeatDTO(Seat s) {
		id = s.getId();
		passport = s.getPassport();
		clientId = s.getClient().getId();
		flightId = s.getFlight().getId();
		flightResId = s.getReservation().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public long getFlightResId() {
		return flightResId;
	}

	public void setFlightResId(long flightResId) {
		this.flightResId = flightResId;
	}
	
}
