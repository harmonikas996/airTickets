package airtickets.dto.aircompany;

import airtickets.model.aircompany.AircompanyRating;

public class AircompanyRatingDTO {
	
	private long id;
	private long clientId;
	private long aircompanyId;
	private int rating;
	
	public AircompanyRatingDTO() {}
	
	public AircompanyRatingDTO(AircompanyRating a) {
		id = a.getId();
		clientId = a.getUser().getId();
		aircompanyId = a.getCompany().getId();
		rating = a.getRating();
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

	public long getAircompanyId() {
		return aircompanyId;
	}

	public void setAircompanyId(long aircompanyId) {
		this.aircompanyId = aircompanyId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
