package airtickets.dto.aircompany;

import airtickets.model.aircompany.FlightRating;

public class FlightRatingDTO {
	
	private long id;
	private long clientId;
	private long flightId;
	private int rating;
	
	public FlightRatingDTO() {}
	
	public FlightRatingDTO(FlightRating f) {
		id = f.getId();
		clientId = f.getUser().getId();
		flightId = f.getFlight().getId();
		rating = f.getRating();
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

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
