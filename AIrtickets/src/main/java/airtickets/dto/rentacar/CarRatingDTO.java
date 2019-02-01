package airtickets.dto.rentacar;

import airtickets.model.rentacar.CarRating;

public class CarRatingDTO {
	
	private long id;
	private long clientId;
	private long vehicleId;
	private int rating;
	
	public CarRatingDTO() {}
	
	public CarRatingDTO(CarRating c) {
		id = c.getId();
		clientId = c.getUser().getId();
		vehicleId = c.getVehicle().getId();
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

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
}
