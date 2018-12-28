package airtickets.model.hotel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import airtickets.model.Company;

@Entity
public class Hotel extends Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430126233932196984L;
	
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Room> rooms;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Amenity> amenities;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<HotelRating> ratings;

	public Hotel() {}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public List<HotelRating> getRatings() {
		return ratings;
	}

}
