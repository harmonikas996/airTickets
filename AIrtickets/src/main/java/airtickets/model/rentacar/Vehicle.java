package airtickets.model.rentacar;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import airtickets.dto.rentacar.VehicleDTO;

@NamedQuery(name="Vehicle.findByRentACarId", query="select v from Vehicle v where (?1 is null or v.rentACar.id = ?1) and v.name = ?2")
//@NamedQuery(name="Vehicle.findByRentACarId", query="select v from Vehicle v where NULLIF(?1,NULL)OR v.rentACar.id = ?1")
// WHERE :value is null or o.category = :value
// COALESCE( null, :listParameter ) is null
//:customer is null or :customer='' or  ord.customer = :customer
@Entity
public class Vehicle implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3007380165050154845L;
	
	
	@Version
	private long version;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

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
	//private double averageRate;
	//private int numberOfRates;
	@Column
	private String image;
	@Column
	private double pricePerDay;
	
	@JoinColumn(name = "rentACar_id")
	@ManyToOne
	private RentACar rentACar;
	
	@OneToMany(mappedBy = "vehicle", cascade=CascadeType.ALL)
	private List<CarRating> ratings;
	@OneToMany(mappedBy = "vehicle")
	private List<CarReservation> reservations;

	public Vehicle() {}

	public Vehicle(VehicleDTO vehicle) {
		id = vehicle.getId();
		name = vehicle.getName();
		brand = vehicle.getBrand();
		model = vehicle.getModel();
		yearOfProduction = vehicle.getYearOfProduction();
		numberOfSeats = vehicle.getNumberOfSeats();
		setType(vehicle.getType());
		image = vehicle.getImage();
		//averageRate = vehicle.getAverageRate();
		//numberOfRates = vehicle.getNumberOfRates();
		pricePerDay = vehicle.getPricePerDay();
		rentACar = new RentACar();
		rentACar.setId(vehicle.getRentACarId());
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

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
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

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<CarRating> getRatings() {
		return ratings;
	}

	public List<CarReservation> getReservations() {
		return reservations;
	}

}
