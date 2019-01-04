package airtickets.dto.aircompany;

import airtickets.model.aircompany.FlightReservation;

public class FlightReservationDTO {
	
	private long id;
	private long flightId;
	private long hotelResId;
	private long carResId;
	
	public FlightReservationDTO() {}
	
	public FlightReservationDTO(FlightReservation f) {
		id = f.getId();
		flightId = f.getFlight().getId();
		hotelResId = f.getHotelReservation().getId();
		carResId = f.getCarReservation().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public long getHotelResId() {
		return hotelResId;
	}

	public void setHotelResId(long hotelResId) {
		this.hotelResId = hotelResId;
	}

	public long getCarResId() {
		return carResId;
	}

	public void setCarResId(long carResId) {
		this.carResId = carResId;
	}

}
