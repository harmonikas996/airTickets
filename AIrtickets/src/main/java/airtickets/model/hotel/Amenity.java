package airtickets.model.hotel;

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
import javax.persistence.OneToMany;

import airtickets.dto.hotel.AmenityDTO;

@Entity
public class Amenity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465983263122799840L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String title;
	@Column
	private double price;
	@JoinColumn(name = "hotel_id")
	@ManyToOne
	private Hotel hotel;
	@OneToMany(mappedBy = "amenity", cascade=CascadeType.ALL)
	private List<Discount> discounts;
	@OneToMany(mappedBy = "amenity", cascade=CascadeType.ALL)
	private List<AmenityReservation> amenityReservation;

	public Amenity() {}

	public Amenity(AmenityDTO amenity) {
		this.id = amenity.getId();
		this.title = amenity.getTitle();
		this.price = amenity.getPrice();
		this.hotel = new Hotel();
		this.hotel.setId(amenity.getHotelId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public List<AmenityReservation> getAmenityReservation() {
		return amenityReservation;
	}
	

}
