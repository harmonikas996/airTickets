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
import airtickets.model.user.User;

@Entity
public class CarRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5484694255026783452L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "vehicle_id")
	@ManyToOne
	private Vehicle vehicle;
	@Column
	private int rating;

	public CarRating() {}
	
	public CarRating(CarRatingDTO c) {
		id = c.getId();
		user = new User();
		user.setId(c.getClientId());
		vehicle = new Vehicle();
		vehicle.setId(c.getVehicleId());
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
