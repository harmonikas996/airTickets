package airtickets.dto.rentacar;

import airtickets.model.rentacar.RentACar;

public class RentACarDTO {

	private long id;
	private String name;
	private String address;
	private String description;
//	private double averageRate;
//	private int numberOfRates;

	public RentACarDTO() {}

	public RentACarDTO(RentACar r) {
		this.id = r.getId();
		this.name = r.getName();
		this.address = r.getAddress();
		this.description = r.getDescription();
//		this.averageRate = r.getAverageRate();
//		this.numberOfRates = r.getNumberOfRates();
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

//	public double getAverageRate() {
//		return averageRate;
//	}
//
//	public void setAverageRate(double averageRate) {
//		this.averageRate = averageRate;
//	}
//
//	public int getNumberOfRates() {
//		return numberOfRates;
//	}
//
//	public void setNumberOfRates(int numberOfRates) {
//		this.numberOfRates = numberOfRates;
//	}

}
