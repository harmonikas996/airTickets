package airtickets.model.hotel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.hotel.RoomRatingDTO;
import airtickets.model.user.User;

@Entity
public class RoomRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321804123657482382L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "room_id")
	@ManyToOne
	private Room room;
	@Column
	private int rating;
	

	public RoomRating() {}

	public RoomRating(RoomRatingDTO roomRating) {
		this.id = roomRating.getId();
		this.user = new User();
		this.user.setId(roomRating.getUserId());
		this.room = new Room();
		this.room.setId(roomRating.getRoomId());
		this.rating = roomRating.getRating();
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
