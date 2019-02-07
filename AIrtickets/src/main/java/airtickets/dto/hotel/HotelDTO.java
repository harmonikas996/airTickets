package airtickets.dto.hotel;

import airtickets.model.hotel.Hotel;

public class HotelDTO {

	private long id;
	private String name;
	private String address;
	private String description;
	private String image;
	private String city;
	
	public HotelDTO() {}
	
	public HotelDTO(Hotel hotel) {
		id = hotel.getId();
		name = hotel.getName();
		address = hotel.getAddress();
		description = hotel.getDescription();
		image = hotel.getImage();
		city = hotel.getCity();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
