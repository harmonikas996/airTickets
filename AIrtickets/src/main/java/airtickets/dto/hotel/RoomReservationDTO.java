package airtickets.dto.hotel;

import airtickets.model.hotel.RoomReservation;

public class RoomReservationDTO {
	
//	private long version;
//	
//	public long getVersion() {
//		return version;
//	}
//
//	public void setVersion(long version) {
//		this.version = version;
//	}
	
	private long id;
	private long roomId;
	private long hotelReservationId;
	
	public RoomReservationDTO() {}
	
	public RoomReservationDTO(RoomReservation roomReservation) {
		id = roomReservation.getId();
		roomId = roomReservation.getRoom().getId();
		hotelReservationId = roomReservation.getHotelReservation().getId();
//		version = roomReservation.getVersion();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getHotelReservationId() {
		return hotelReservationId;
	}

	public void setHotelReservationId(long hotelReservationId) {
		this.hotelReservationId = hotelReservationId;
	}
}
