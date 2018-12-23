package airtickets.model.hotel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4854194133193581618L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private double percentage;
	@ManyToMany
    @JoinTable(name = "discounts_amenities",
            joinColumns = { @JoinColumn(name = "discount_id") },
            inverseJoinColumns = { @JoinColumn(name = "amenity_id") })
	private List<Amenity> amenities;

	public Discount() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

}
