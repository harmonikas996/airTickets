package airtickets.model.rentacar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.rentacar.CarRatingDTO;
import airtickets.dto.rentacar.RentACarRatingDTO;
import airtickets.model.user.User;

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
	private User user;
	@JoinColumn(name = "rentacar_id")
	@ManyToOne
	private RentACar rentACar;
	@Column
	private int rating;

	public RentACarRating() {}
	
	public RentACarRating(RentACarRatingDTO c) {
		id = c.getId();
		user = new User();
		user.setId(c.getClientId());
		rentACar = new RentACar();
		rentACar.setId(c.getRentACarId());
		rating = c.getRating();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
