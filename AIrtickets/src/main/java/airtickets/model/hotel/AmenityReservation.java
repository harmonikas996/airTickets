package airtickets.model.hotel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.hotel.AmenityReservationDTO;

@Entity
public class AmenityReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8852133278546528410L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "amenity_id")
	@ManyToOne
	private Amenity amenity;
	@JoinColumn(name = "hotelReservation_id")
	@ManyToOne
	private HotelReservation hotelReservation;

	public AmenityReservation() {}

	public AmenityReservation(AmenityReservationDTO amenityReservation) {
		this.id = amenityReservation.getId();
		this.amenity.setId(amenityReservation.getAmenityId());
		this.hotelReservation.setId(amenityReservation.getHotelReservationId());;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public HotelReservation getHotelReservation() {
		return hotelReservation;
	}

	public void setHotelReservation(HotelReservation hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

}
