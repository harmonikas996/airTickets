package airtickets.dto.hotel;

import airtickets.model.hotel.Amenity;

public class AmenityDTO {
	
	private long id;
	private String title;
	private double price;
	private long hotelId;
	
	public AmenityDTO() {}
	
	public AmenityDTO(Amenity amenity) {
		id = amenity.getId();
		title = amenity.getTitle();
		price = amenity.getPrice();
		hotelId = amenity.getHotel().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
}
