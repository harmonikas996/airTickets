package airtickets.model.hotel;

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
import javax.persistence.OneToOne;

import airtickets.dto.hotel.HotelReservationDTO;
import airtickets.model.aircompany.FlightReservation;

@Entity
public class HotelReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3134647799978892038L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "hotel_id")
	@ManyToOne
	private Hotel hotel;
	@Column
	private LocalDateTime dateFrom;
	@Column
	private LocalDateTime dateTo;
	@Column
	private double price;
	@OneToMany(mappedBy = "hotelReservation", cascade=CascadeType.ALL)
	private List<RoomReservation> roomReservations;
	@OneToMany(mappedBy = "hotelReservation", cascade=CascadeType.ALL)
	private List<AmenityReservation> amenityReservations;
	
	//@JoinColumn(name = "flight_reservation_id")
	@OneToOne(mappedBy = "hotelReservation")
	private FlightReservation flightReservation;

	public HotelReservation() {}

	public HotelReservation(HotelReservationDTO hotelReservation) {
		this.id = hotelReservation.getId();
		this.hotel = new Hotel();
		this.hotel.setId(hotelReservation.getHotel());
		this.dateFrom = hotelReservation.getDateFrom();
		this.dateTo = hotelReservation.getDateTo();
		this.price = hotelReservation.getPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	public List<RoomReservation> getRoomReservations() {
		return roomReservations;
	}

	public List<AmenityReservation> getAmenityReservations() {
		return amenityReservations;
	}

	public FlightReservation getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}

}
