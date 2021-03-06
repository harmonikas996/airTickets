package airtickets.dto.rentacar;

import airtickets.model.rentacar.Vehicle;

public class VehicleDTO {
	
	private long version;
	private long id;
	private String name;
	private String brand;
	private String model;
	private int yearOfProduction;
	private int numberOfSeats;
	private String type;
	//private double averageRate;
	//private int numberOfRates;
	private double pricePerDay;
	private long rentACarId;
	private String image;

	public VehicleDTO() {}

	public VehicleDTO(Vehicle vehicle) {
		id = vehicle.getId();
		name = vehicle.getName();
		brand = vehicle.getBrand();
		model = vehicle.getModel();
		yearOfProduction = vehicle.getYearOfProduction();
		numberOfSeats = vehicle.getNumberOfSeats();
		type = vehicle.getTypeString();
		image = vehicle.getImage();
		//averageRate = vehicle.getAverageRate();
		//numberOfRates = vehicle.getNumberOfRates();
		pricePerDay = vehicle.getPricePerDay();
		rentACarId = vehicle.getRentACar().getId();
		version = vehicle.getVersion();
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(long rentACarId) {
		this.rentACarId = rentACarId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
