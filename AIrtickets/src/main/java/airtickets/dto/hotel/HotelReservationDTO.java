package airtickets.dto.hotel;

import java.time.LocalDateTime;

import airtickets.model.hotel.HotelReservation;

public class HotelReservationDTO {
	private long id;
	private long hotel;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private double price;
	
	public HotelReservationDTO() {}
	
	public HotelReservationDTO(HotelReservation hotelReservation) {
		id = hotelReservation.getId();
		hotel = hotelReservation.getHotel().getId();
		dateFrom = hotelReservation.getDateFrom();
		dateTo = hotelReservation.getDateTo();
		price = hotelReservation.getPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHotel() {
		return hotel;
	}

	public void setHotel(long hotel) {
		this.hotel = hotel;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
