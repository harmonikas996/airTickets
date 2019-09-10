package airtickets.dto.aircompany;

import airtickets.model.aircompany.Seat;

public class SeatDTO {
	
	private long id;
	private Long clientId;
	private String passport;
	private long flightId;
	private Long flightResId;
	private Double price;
	private String mark;
	private String firstName;
	private String lastName;
	private String contact;
	
	private long version;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public SeatDTO() {}
	
	public SeatDTO(Seat s) {
		id = s.getId();
		passport = s.getPassport();
		if (s.getClient() != null)
			clientId = s.getClient().getId();
		flightId = s.getFlight().getId();
		if (s.getReservation() != null)
			flightResId = s.getReservation().getId();
		price = s.getPrice();
		mark = s.getMark();
		firstName = s.getFirstName();
		lastName = s.getLastName();
		contact = s.getContact();
		version = s.getVersion();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
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

	public Long getFlightResId() {
		return flightResId;
	}

	public void setFlightResId(Long flightResId) {
		this.flightResId = flightResId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
