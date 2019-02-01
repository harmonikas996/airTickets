package airtickets.model.hotel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.dto.hotel.RoomReservationDTO;

@Entity
public class RoomReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6872517284834872418L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "room_id")
	@ManyToOne
	private Room room;
	@JoinColumn(name = "hotelReservation_id")
	@ManyToOne
	private HotelReservation hotelReservation;
	
//	@JoinColumn(name = "hotel_id")
//	@ManyToOne
//	private Hotel hotel;

	public RoomReservation() {}

	public RoomReservation(RoomReservationDTO roomReservation) {
		this.id = roomReservation.getId();
		this.room = new Room();
		this.room.setId(roomReservation.getRoomId());
		this.hotelReservation = new HotelReservation();
		this.hotelReservation.setId(roomReservation.getHotelReservationId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HotelReservation getHotelReservation() {
		return hotelReservation;
	}

	public void setHotelReservation(HotelReservation hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

//	public Hotel getHotel() {
//		return hotel;
//	}
//
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}

}
