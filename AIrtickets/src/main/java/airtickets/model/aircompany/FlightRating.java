package airtickets.model.aircompany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.aircompany.FlightRatingDTO;
import airtickets.model.user.Client;

@Entity
public class FlightRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1250948464407409944L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "user_id")
	@ManyToOne
	private Client user;
	@JoinColumn(name = "flight_id")
	@ManyToOne
	private Flight flight;
	@Column
	private int rating;

	public FlightRating() {}
	
	public FlightRating(FlightRatingDTO f) {
		id = f.getId();
		user = new Client();
		user.setId(f.getClientId());
		flight = new Flight();
		flight.setId(f.getFlightId());
		rating = f.getRating();
	}

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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
