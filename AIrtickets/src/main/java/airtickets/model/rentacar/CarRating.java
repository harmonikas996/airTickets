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
	private Client user;
	@JoinColumn(name = "vehicle_id")
	@ManyToOne
	private Vehicle vehicle;
	@Column
	private int rating;

	public CarRating() {}

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
