package airtickets.model.rentacar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.rentacar.VehicleDTO;

@Entity
public class Vehicle implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3007380165050154845L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String brand;
	@Column
	private String model;
	@Column
	private int yearOfProduction;
	@Column
	private int numberOfSeats;
	@Column
	private CarType type;
	@Column
	private double averageRate;
	@Column
	private int numberOfRates;
	@Column
	private double pricePerDay;
	@JoinColumn(name = "branch_id")
	@ManyToOne
	private BranchOffice branch;

	public Vehicle() {}

	public Vehicle(VehicleDTO vehicle) {
		id = vehicle.getId();
		name = vehicle.getName();
		brand = vehicle.getBrand();
		model = vehicle.getModel();
		yearOfProduction = vehicle.getYearOfProduction();
		numberOfSeats = vehicle.getNumberOfSeats();
		numberOfRates = vehicle.getNumberOfRates();
		setType(vehicle.getType());
		averageRate = vehicle.getAverageRate();
		numberOfRates = vehicle.getNumberOfRates();
		pricePerDay = vehicle.getPricePerDay();
		branch = new BranchOffice();
		branch.setId(vehicle.getBranchId());
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

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public int getNumberOfRates() {
		return numberOfRates;
	}

	public void setNumberOfRates(int numberOfRates) {
		this.numberOfRates = numberOfRates;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public BranchOffice getBranch() {
		return branch;
	}

	public void setBranch(BranchOffice branch) {
		this.branch = branch;
	}

	public void setType(String type) {
		if (type.equals("Sedan"))
			this.type = CarType.SEDAN;
		else if (type.equals("Station Wagon"))
			this.type = CarType.STATION_WAGON;
		else if (type.equals("Van"))
			this.type = CarType.VAN;
		else
			this.type = CarType.SUV;
	}

	public String getTypeString() {
		if (type.equals(CarType.SEDAN))
			return "Sedan";
		else if (type.equals(CarType.STATION_WAGON))
			return "Station Wagon";
		else if (type.equals(CarType.VAN))
			return "Van";
		return "SUV";
	}
}
