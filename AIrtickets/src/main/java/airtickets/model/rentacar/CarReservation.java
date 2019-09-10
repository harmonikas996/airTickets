package airtickets.model.rentacar;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import airtickets.dto.rentacar.CarReservationDTO;
import airtickets.model.aircompany.FlightReservation;

@Entity
public class CarReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4426569286511251516L;
	
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
	@JoinColumn(name = "vehicle_id")
	@ManyToOne
	private Vehicle vehicle;
	@Column
	private LocalDateTime dateFrom;
	@Column
	private LocalDateTime dateTo;
	@Column
	private double price;
	
	//@JoinColumn(name = "flight_reservation_id")
	@OneToOne(mappedBy = "carReservation")
	private FlightReservation flightReservation;
	
//	@JoinColumn(name = "rentacar_id")
//	@ManyToOne
//	private RentACar rentACar;

	public CarReservation() {}

	public CarReservation(CarReservationDTO c) {
		this.id = c.getId();
		this.vehicle = new Vehicle();
		this.vehicle.setId(c.getVehicleId());
		this.dateFrom = c.getDateFrom();
		this.dateTo = c.getDateTo();
		this.price = c.getPrice();
//		this.rentACar = new RentACar();
//		this.rentACar.setId(c.getRentACarId());
		this.version = c.getVersion();
	}

//	public RentACar getRentACar() {
//		return rentACar;
//	}
//
//	public void setRentACar(RentACar rentACar) {
//		this.rentACar = rentACar;
//	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FlightReservation getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}

}
