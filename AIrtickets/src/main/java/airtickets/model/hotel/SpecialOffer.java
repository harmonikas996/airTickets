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
public class SpecialOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4854194133193581618L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private double percentage;
	@Column
	private String title;
	@OneToMany(mappedBy = "offer", cascade=CascadeType.ALL)
	private List<Discount> discounts;

	public SpecialOffer() {}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

}
