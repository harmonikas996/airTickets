package airtickets.model.hotel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430126233932196984L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Room> rooms;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<Amenity> amenities;
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	private List<HotelRating> ratings;

	public Hotel() {}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

}
