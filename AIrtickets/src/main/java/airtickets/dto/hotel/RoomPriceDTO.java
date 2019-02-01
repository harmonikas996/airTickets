package airtickets.dto.hotel;

import java.time.LocalDateTime;

import airtickets.model.hotel.RoomPrice;

public class RoomPriceDTO {
	private long id;
	private LocalDateTime datoFrom;
	private LocalDateTime datoTo;
	private double price;
	private long roomId;
	
	public RoomPriceDTO() {}
	
	public RoomPriceDTO(RoomPrice roomPrice) {
		id = roomPrice.getId();
		datoFrom = roomPrice.getDatoFrom();
		datoTo = roomPrice.getDatoTo();
		price = roomPrice.getPrice();
		roomId = roomPrice.getRoom().getId();
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

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
}
