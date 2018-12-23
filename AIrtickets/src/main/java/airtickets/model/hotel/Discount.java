package airtickets.model.hotel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4854194133193581618L;
	@Id
	private long id;
	@JoinColumn(name = "amenity_id")
	@ManyToOne
	private Amenity amenity;
	@JoinColumn(name = "offer_id")
	@ManyToOne
	private SpecialOffer offer;

	public Discount() {}

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

	public SpecialOffer getOffer() {
		return offer;
	}

	public void setOffer(SpecialOffer offer) {
		this.offer = offer;
	}

}
