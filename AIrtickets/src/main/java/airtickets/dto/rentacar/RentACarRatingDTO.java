package airtickets.dto.rentacar;

import airtickets.model.rentacar.RentACarRating;

public class RentACarRatingDTO {
	
	private long id;
	private long clientId;
	private long rentACarId;
	private int rating;
	
	public RentACarRatingDTO() {}
	
	public RentACarRatingDTO(RentACarRating c) {
		id = c.getId();
		clientId = c.getUser().getId();
		rentACarId = c.getRentACar().getId();
		rating = c.getRating();
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

	public long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(long rentACarId) {
		this.rentACarId = rentACarId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
