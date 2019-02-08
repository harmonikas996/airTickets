package airtickets.dto.aircompany;

import airtickets.model.aircompany.FlightReservation;

public class FlightReservationDTO {
	
	private long id;
	private Long flightId;
	private Long hotelResId;
	private Long carResId;
	
	public FlightReservationDTO() {}
	
	public FlightReservationDTO(FlightReservation f) {
		id = f.getId();
		if (f.getFlight() != null)
			flightId = f.getFlight().getId();
		if (f.getHotelReservation() != null)
			hotelResId = f.getHotelReservation().getId();
		if (f.getCarReservation() != null)
			carResId = f.getCarReservation().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getHotelResId() {
		return hotelResId;
	}

	public void setHotelResId(Long hotelResId) {
		this.hotelResId = hotelResId;
	}

	public Long getCarResId() {
		return carResId;
	}

	public void setCarResId(Long carResId) {
		this.carResId = carResId;
	}

}
