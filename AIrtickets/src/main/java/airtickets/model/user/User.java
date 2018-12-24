package airtickets.model.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import airtickets.model.aircompany.AircompanyRating;
import airtickets.model.aircompany.FlightRating;
import airtickets.model.hotel.HotelRating;
import airtickets.model.hotel.RoomRating;
import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.RentACarRating;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596875042865462761L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String city;
	@Column
	private String phone;
	@Column
	private boolean activated;
	@Column
	private int bonusPoints;
//	@Column
//	private String passport;
	@OneToMany(mappedBy = "initier", cascade=CascadeType.ALL)
	private List<Friendship> added;
	@OneToMany(mappedBy = "confirmer", cascade=CascadeType.ALL)
	private List<Friendship> addedBy;
	@OneToMany(mappedBy = "inviter", cascade=CascadeType.ALL)
	private List<Invitation> invited;
	@OneToMany(mappedBy = "invited", cascade=CascadeType.ALL)
	private List<Invitation> invitedBy;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<FlightRating> flightRatings;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<RoomRating> roomRatings;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<HotelRating> hotelRatings;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<CarRating> carRatings;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<RentACarRating> rentACarRatings;
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<AircompanyRating> aircompanyRatings;

	public User() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public List<Friendship> getAdded() {
		return added;
	}

	public List<Friendship> getAddedBy() {
		return addedBy;
	}

	public List<Invitation> getInvited() {
		return invited;
	}

	public List<Invitation> getInvitedBy() {
		return invitedBy;
	}

	public List<FlightRating> getFlightRatings() {
		return flightRatings;
	}

	public List<RoomRating> getRoomRatings() {
		return roomRatings;
	}

	public List<HotelRating> getHotelRatings() {
		return hotelRatings;
	}

	public List<CarRating> getCarRatings() {
		return carRatings;
	}

	public List<RentACarRating> getRentACarRatings() {
		return rentACarRatings;
	}

	public List<AircompanyRating> getAircompanyRatings() {
		return aircompanyRatings;
	}


//	public String getPassport() {
//		return passport;
//	}
//
//	public void setPassport(String passport) {
//		this.passport = passport;
//	}

}
