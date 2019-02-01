package airtickets.model.hotel;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.hotel.RoomPriceDTO;

@Entity
public class RoomPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1941586444887160276L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private LocalDateTime datoFrom;
	@Column
	private LocalDateTime datoTo;
	@Column
	private double price;
	@JoinColumn(name = "room_id")
	@ManyToOne
	private Room room;

	public RoomPrice() {}

	public RoomPrice(RoomPriceDTO roomPrice) {
		this.id = roomPrice.getId();
		this.datoFrom = roomPrice.getDatoFrom();
		this.datoTo = roomPrice.getDatoTo();
		this.price = roomPrice.getPrice();
		this.room = new Room();
		this.room.setId(roomPrice.getRoomId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatoFrom() {
		return datoFrom;
	}

	public void setDatoFrom(LocalDateTime datoFrom) {
		this.datoFrom = datoFrom;
	}

	public LocalDateTime getDatoTo() {
		return datoTo;
	}

	public void setDatoTo(LocalDateTime datoTo) {
		this.datoTo = datoTo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
