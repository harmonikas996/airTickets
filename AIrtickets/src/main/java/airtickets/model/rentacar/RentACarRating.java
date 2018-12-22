package airtickets.model.rentacar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RentACarRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4355803515765744595L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	// ovo obelezje je privremeno, sluzi tu dok ne izradimo klasu Korisnik, nakon cega ce biti zamenjeno stranim kljucem
	@Column
	private long userId;
	@JoinColumn(name = "rentacar_id")
	@ManyToOne
	private RentACar rentACar;
	@Column
	private int rating;

	public RentACarRating() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
