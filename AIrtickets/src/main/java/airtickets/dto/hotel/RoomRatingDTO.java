package airtickets.dto.hotel;

import airtickets.model.hotel.RoomRating;

public class RoomRatingDTO {
	private long id;
	private long userId;
	private long roomId;
	private int rating;
	
	public RoomRatingDTO() {}
	
	public RoomRatingDTO(RoomRating roomRating) {
		id = roomRating.getId();
		userId = roomRating.getUser().getId();
		roomId = roomRating.getRoom().getId();
		rating = roomRating.getRating();
	}

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

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
