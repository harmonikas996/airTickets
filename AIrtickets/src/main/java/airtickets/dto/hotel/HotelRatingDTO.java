package airtickets.dto.hotel;

import airtickets.model.hotel.HotelRating;

public class HotelRatingDTO {
	private long id;
	private long userId;
	private long hotelId;
	private int rating;
	
	public HotelRatingDTO() {}

	public HotelRatingDTO(HotelRating hotelRating) {
		id = hotelRating.getId();
		userId = hotelRating.getUser().getId();
		hotelId = hotelRating.getHotel().getId();
		rating = hotelRating.getRating();
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

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
