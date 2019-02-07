package airtickets.model.hotel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import airtickets.dto.hotel.HotelDTO;
import airtickets.model.Company;

@Entity
public class Hotel extends Company implements Serializable{

	/**
	 * 
	 */
	@Column
	private String city;
	
	private static final long serialVersionUID = 3430126233932196984L;
	
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Room> rooms;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Amenity> amenities;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<HotelRating> ratings;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<HotelReservation> reservations;

	public Hotel() {}

	public Hotel(HotelDTO hotel) {
		super(hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getDescription(), hotel.getImage());
		city = hotel.getCity();
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public List<HotelRating> getRatings() {
		return ratings;
	}

	public List<HotelReservation> getReservations() {
		return reservations;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
