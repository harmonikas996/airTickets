package airtickets.dto.aircompany;

import airtickets.model.aircompany.Airport;

public class AirportDTO {
	
	private long id;
	private String name;
	private String city;
	private String address;
	
	public AirportDTO() {}
	
	public AirportDTO(Airport a) {
		id = a.getId();
		name = a.getName();
		city = a.getCity();
		address = a.getAddress();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
		
}
