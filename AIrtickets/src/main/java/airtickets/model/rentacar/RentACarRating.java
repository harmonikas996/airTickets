package airtickets.model.rentacar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.model.user.Client;

@Entity
public class RentACarRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4355803515765744595L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "user_id")
	@ManyToOne
	private Client user;
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

	public Client getUser() {
		return user;
	}

	public void setUser(Client user) {
		this.user = user;
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
