package airtickets.model.user;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import airtickets.dto.user.UserDTO;
import airtickets.model.Company;
import airtickets.model.aircompany.Aircompany;
import airtickets.model.aircompany.AircompanyRating;
import airtickets.model.aircompany.FlightRating;
import airtickets.model.aircompany.Seat;
import airtickets.model.hotel.HotelRating;
import airtickets.model.hotel.RoomRating;
import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.CarType;
import airtickets.model.rentacar.RentACarRating;

@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609942580148633635L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String email;
	@Column
	private String password;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
	
	@ManyToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },
    mappedBy = "users")
    private Set<Authority> authorities;
	
	// admin
	
	@JoinColumn(name = "company_id")
	@ManyToOne
	private Company company;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	//
	
	// client
	
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
	
	@Column
	private UserType type;
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
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private List<Seat> reservations;
	
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

	public List<Seat> getReservations() {
		return reservations;
	}
	
	//
	
	public User() {
		this.bonusPoints = 0;
	}

	public User(long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.bonusPoints = 0;
	}

	public User(UserDTO user) {
		this.id = user.getId();
		this.email = user.getEmail();
		if (user.getCompany() != 0) {
			this.company = new Company();
			this.company.setId(user.getCompany());
		}
		else {
			this.company = null;
		}
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.activated = user.isActivated();
		this.city = user.getCity();		
		this.activated = user.isActivated();
		this.bonusPoints = user.getBonusPoints();
		this.lastPasswordResetDate = user.getLastPasswordResetDate();
		setType(user.getType());
	}

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
		if (!password.equals(this.password)) {    
			Timestamp now = new Timestamp(DateTime.now().getMillis());
	        this.setLastPasswordResetDate( now );
			this.password = password;
		}
	}
	
	public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
	
	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
	
	public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
	
	public String getType() {
		if (type.equals(UserType.sysadmin))
			return "sysadmin";
		else if (type.equals(UserType.rentacar))
			return "rentacar";
		else if (type.equals(UserType.hotel))
			return "hotel";
		else if (type.equals(UserType.aircompany))
			return "aircompany";
		return "client";
	}

	public void setType(String type) {
		if (type.equals("sysadmin"))
			this.type = UserType.sysadmin;
		else if (type.equals("rentacar"))
			this.type = UserType.rentacar;
		else if (type.equals("hotel"))
			this.type = UserType.hotel;
		else if (type.equals("aircompany"))
			this.type = UserType.aircompany;
		else
			this.type = UserType.client;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return activated;
	}
}
