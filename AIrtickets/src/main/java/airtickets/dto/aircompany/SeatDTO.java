package airtickets.dto.aircompany;

import airtickets.model.aircompany.Seat;

public class SeatDTO {
	
	private long id;
	private long clientId;
	private String passport;
	private long flightId;
	private long flightResId;
	private double price;
	private String mark;
	private String firstName;
	private String lastName;
	private String contact;
	
	public SeatDTO() {}
	
	public SeatDTO(Seat s) {
		id = s.getId();
		passport = s.getPassport();
		clientId = s.getClient().getId();
		flightId = s.getFlight().getId();
		flightResId = s.getReservation().getId();
		price = s.getPrice();
		mark = s.getMark();
		firstName = s.getFirstName();
		lastName = s.getLastName();
		contact = s.getContact();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public long getFlightResId() {
		return flightResId;
	}

	public void setFlightResId(long flightResId) {
		this.flightResId = flightResId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
