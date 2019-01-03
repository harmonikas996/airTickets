package airtickets.dto.aircompany;

import java.time.LocalDateTime;

import airtickets.model.aircompany.Flight;

public class FlightDTO {
	
	private long id;
	private LocalDateTime timeBegin;
	private LocalDateTime timeEnd;
	private double distance;
	private double price;
	private String airplaneType;
	private double loweredPrice;
	private long placeFromId;
	private long placeToId;
	private long aircompanyId;
	
	public FlightDTO() {}
	
	public FlightDTO(Flight f) {
		id = f.getId();
		timeBegin = f.getTimeBegin();
		timeEnd = f.getTimeEnd();
		distance = f.getDistance();
		price = f.getPrice();
		airplaneType = f.getTypeString();
		loweredPrice = f.getLoweredPrice();
		placeFromId = f.getPlaceFrom().getId();
		placeToId = f.getPlaceTo().getId();
		aircompanyId = f.getCompany().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(LocalDateTime timeBegin) {
		this.timeBegin = timeBegin;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAirplaneType() {
		return airplaneType;
	}

	public void setAirplaneType(String airplaneType) {
		this.airplaneType = airplaneType;
	}

	public double getLoweredPrice() {
		return loweredPrice;
	}

	public void setLoweredPrice(double loweredPrice) {
		this.loweredPrice = loweredPrice;
	}

	public long getPlaceFromId() {
		return placeFromId;
	}

	public void setPlaceFromId(long placeFromId) {
		this.placeFromId = placeFromId;
	}

	public long getPlaceToId() {
		return placeToId;
	}

	public void setPlaceToId(long placeToId) {
		this.placeToId = placeToId;
	}

	public long getAircompanyId() {
		return aircompanyId;
	}

	public void setAircompanyId(long aircompanyId) {
		this.aircompanyId = aircompanyId;
	}
}
