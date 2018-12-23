package airtickets.dto.rentacar;

import java.time.LocalDateTime;

import airtickets.model.rentacar.CarReservation;

public class CarReservationDTO {
	
	private long id;
	private long vehicleId;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private double price;
//	private long rentACarId;
	
	public CarReservationDTO() {}
	
	public CarReservationDTO(CarReservation c) {
		this.id = c.getId();
		this.vehicleId = c.getVehicle().getId();
		this.dateFrom = c.getDateFrom();
		this.dateTo = c.getDateTo();
		this.price = c.getPrice();
//		this.rentACarId = c.getRentACar().getId();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
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
//	public long getRentACarId() {
//		return rentACarId;
//	}
//	public void setRentACarId(long rentACarId) {
//		this.rentACarId = rentACarId;
//	}
}
