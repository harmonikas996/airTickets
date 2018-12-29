package airtickets.model.aircompany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import airtickets.model.hotel.HotelReservation;
import airtickets.model.rentacar.CarReservation;

@Entity
public class FlightReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8082925500967862827L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "flight_id")
	@ManyToOne
	private Flight flight;
	@OneToMany(mappedBy = "reservation", cascade=CascadeType.ALL)
	private List<Seat> seats;
	
	@JoinColumn(name = "hotel_reservation_id")
	@OneToOne(cascade=CascadeType.ALL)
	private HotelReservation hotelReservation;
	
	@JoinColumn(name = "car_reservation_id")
	@OneToOne(cascade=CascadeType.ALL)
	private CarReservation carReservation;

	public FlightReservation() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public HotelReservation getHotelReservation() {
		return hotelReservation;
	}

	public void setHotelReservation(HotelReservation hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

	public CarReservation getCarReservation() {
		return carReservation;
	}

	public void setCarReservation(CarReservation carReservation) {
		this.carReservation = carReservation;
	}

	public List<Seat> getSeats() {
		return seats;
	}

}
