package airtickets.dto.hotel;

import airtickets.model.hotel.AmenityReservation;

public class AmenityReservationDTO {
	private long id;
	private long amenityId;
	private long hotelReservationId;
	
	public AmenityReservationDTO() {}
	
	public AmenityReservationDTO(AmenityReservation amenityReservation) {
		id = amenityReservation.getId();
		amenityId = amenityReservation.getAmenity().getId();
		hotelReservationId = amenityReservation.getHotelReservation().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(long amenityId) {
		this.amenityId = amenityId;
	}

	public long getHotelReservationId() {
		return hotelReservationId;
	}

	public void setHotelReservationId(long hotelReservationId) {
		this.hotelReservationId = hotelReservationId;
	}
}
