package airtickets.model.aircompany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import airtickets.dto.aircompany.SeatDTO;
import airtickets.model.user.Invitation;
import airtickets.model.user.User;

@Entity
public class Seat implements Serializable {

	/**
	 * Flight
	 */
	private static final long serialVersionUID = 2993706379896649796L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@JoinColumn(name = "client_id")
	@ManyToOne
	private User client;
	@Column
	private double price;
	@Column
	private String mark;
	@Column
	private String passport;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String contact;
	@JoinColumn(name = "flight_id")
	@ManyToOne
	private Flight flight;
	@JoinColumn(name = "reservation_id")
	@ManyToOne
	private FlightReservation reservation;
	@OneToMany(mappedBy = "seat", cascade=CascadeType.ALL)
	private List<Invitation> invitations;

	public Seat() {}
	
	public Seat(SeatDTO s) {
		id = s.getId();
		client = new User();
		client.setId(s.getClientId());
		passport = s.getPassport();
		flight = new Flight();
		flight.setId(s.getFlightId());
		reservation =new FlightReservation();
		reservation.setId(s.getFlightResId());
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

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightReservation getReservation() {
		return reservation;
	}

	public void setReservation(FlightReservation reservation) {
		this.reservation = reservation;
	}

	public List<Invitation> getInvitations() {
		return invitations;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
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
