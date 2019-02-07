package airtickets.dto.hotel;

import airtickets.model.hotel.Room;

public class RoomDTO {
	private long id;
	private int number;
	private int floor;
	private int noOfBeds;
	private String type;
	private long hotel;
	private String image;
	
	public RoomDTO() {}
	
	public RoomDTO(Room room) {
		id = room.getId();
		number = room.getNumber();
		floor = room.getFloor();
		noOfBeds = room.getNoOfBeds();
		type = room.getType();
		hotel = room.getHotel().getId();
		image = room.getImage();
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

	public long getHotel() {
		return hotel;
	}

	public void setHotel(long hotelId) {
		this.hotel = hotelId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
