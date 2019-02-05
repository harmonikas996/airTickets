package airtickets.model.aircompany;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

import airtickets.dto.aircompany.FlightDTO;

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
	
	public Flight(FlightDTO f) {
		this.id = f.getId();
		
		
		this.timeBegin = f.getTimeBegin();
		this.timeEnd = f.getTimeEnd();
		
//		String time = f.getTimeBegin();		
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");		
//		this.timeBegin = LocalDateTime.parse(time, df);
//		
//		String time2 = f.getTimeEnd();		
//		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");		
//		this.timeEnd = LocalDateTime.parse(time2, df2);
//		
		this.distance = f.getDistance();
		this.price = f.getPrice();
			
		if (f.getAirplaneType().equals("AirbusA320"))
			this.airplaneType = AirplaneType.AirbusA320;
		else if (f.getAirplaneType().equals("Boeing747"))
			this.airplaneType = AirplaneType.Boeing747;
		else
			this.airplaneType = AirplaneType.Boeing777;
		
		this.loweredPrice = f.getLoweredPrice();
		this.placeFrom = new Airport();
		this.placeFrom.setId(f.getPlaceFromId());
		
		this.placeTo = new Airport();
		this.placeTo.setId(f.getPlaceToId());
		
		this.company = new Aircompany();
		this.company.setId(f.getAircompanyId());
		
	}

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

	public void setAirplaneType(String airplaneType) {
		if (airplaneType.equals("AirbusA320"))
			this.airplaneType = AirplaneType.AirbusA320;
		else if (airplaneType.equals("Boeing747"))
			this.airplaneType = AirplaneType.Boeing747;
		else
			this.airplaneType = AirplaneType.Boeing777;
	}

	public String getTypeString() {
		if (airplaneType.equals(AirplaneType.AirbusA320))
			return "AirbusA320";
		else if (airplaneType.equals(AirplaneType.Boeing747))
			return "Boeing747";
		return "Boeing777";
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
