package airtickets.model.hotel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import airtickets.dto.hotel.RoomDTO;

@Entity
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1205005173050276856L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private int number;
	@Column
	private int floor;
	@Column
	private int noOfBeds;
	@Column
	private String type;
	@JoinColumn(name = "hotel_id")
	@ManyToOne
	private Hotel hotel;
	@OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
	private List<RoomRating> ratings;
	@OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
	private List<RoomPrice> prices;
	@OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
	private List<RoomReservation> reservations;

	public Room() {}

	public Room(RoomDTO room) {
		this.id = room.getId();
		this.number = room.getNumber();
		this.floor = room.getFloor();
		this.noOfBeds = room.getNoOfBeds();
		this.type = room.getType();
		this.hotel = new Hotel();
		this.hotel.setId(room.getHotelId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNoOfBeds() {
		return noOfBeds;
	}

	public void setNoOfBeds(int noOfBeds) {
		this.noOfBeds = noOfBeds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<RoomRating> getRatings() {
		return ratings;
	}

	public List<RoomPrice> getPrices() {
		return prices;
	}

	public List<RoomReservation> getReservations() {
		return reservations;
	}

}
