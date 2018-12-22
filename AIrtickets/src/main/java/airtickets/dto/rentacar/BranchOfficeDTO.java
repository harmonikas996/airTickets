package airtickets.dto.rentacar;

import airtickets.model.rentacar.BranchOffice;

public class BranchOfficeDTO {
	
	private long id;
	private long rentACarId;
	private String address;
	private String city;
	
	public BranchOfficeDTO() {}
	
	public BranchOfficeDTO(BranchOffice b) {
		this.id = b.getId();
		this.rentACarId = b.getRentACar().getId();
		this.address = b.getAddress();
		this.city = b.getCity();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(long rentACarId) {
		this.rentACarId = rentACarId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
