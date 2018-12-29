package airtickets.model.aircompany;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2601282512499947023L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private LocalDateTime timeBegin;
	@Column
	private LocalDateTime timeEnd;
	@Column
	private double distance;
	@Column
	private double price;
	@Column
	private AirplaneType airplaneType;
	@Column
	private double loweredPrice;
	@JoinColumn(name = "from_id")
	@ManyToOne
	private Airport placeFrom;
	@JoinColumn(name = "destination_id")
	@ManyToOne
	private Airport placeTo;
	@OneToMany(mappedBy = "flight", cascade=CascadeType.ALL)
	private List<Stop> stops;
	@OneToMany(mappedBy = "flight", cascade=CascadeType.ALL)
	private List<Seat> seats;
	@JoinColumn(name = "company_id")
	@ManyToOne
	private Aircompany company;
	@OneToMany(mappedBy = "flight", cascade=CascadeType.ALL)
	private List<FlightRating> ratings;
	@OneToMany(mappedBy = "flight")
	private List<FlightReservation> reservations;

	public Flight() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(LocalDateTime timeBegin) {
		this.timeBegin = timeBegin;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLoweredPrice() {
		return loweredPrice;
	}

	public void setLoweredPrice(double loweredPrice) {
		this.loweredPrice = loweredPrice;
	}

	public Airport getPlaceFrom() {
		return placeFrom;
	}

	public void setPlaceFrom(Airport placeFrom) {
		this.placeFrom = placeFrom;
	}

	public Airport getPlaceTo() {
		return placeTo;
	}

	public void setPlaceTo(Airport placeTo) {
		this.placeTo = placeTo;
	}

	public AirplaneType getAirplaneType() {
		return airplaneType;
	}

	public void setAirplaneType(AirplaneType airplaneType) {
		this.airplaneType = airplaneType;
	}

	public List<Stop> getStops() {
		return stops;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public Aircompany getCompany() {
		return company;
	}

	public void setCompany(Aircompany company) {
		this.company = company;
	}

	public List<FlightRating> getRatings() {
		return ratings;
	}

	public List<FlightReservation> getReservations() {
		return reservations;
	}

}
