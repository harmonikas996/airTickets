package airtickets.model.hotel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HotelRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5908790673910335036L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	// ovo obelezje je privremeno, sluzi tu dok ne izradimo klasu Korisnik, nakon cega ce biti zamenjeno stranim kljucem
	@Column
	private long userId;
	@JoinColumn(name = "hotel_id")
	@ManyToOne
	private Hotel hotel;
	@Column
	private int rating;

	public HotelRating() {}

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

	public Hotel getHotel() {
		return hotel;
	}

	public void setRentACar(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
