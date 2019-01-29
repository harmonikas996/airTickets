package airtickets.dto.user;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Column;

import airtickets.model.user.User;

public class UserDTO {
	
	private long id;
	private String email;
	private long company;
	private String firstName;
	private String lastName;
	private String city;
	private String phone;
	private boolean activated;
	private int bonusPoints;
	private Timestamp lastPasswordResetDate;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User u) {
		id = u.getId();
		email = u.getEmail();
		if(u.getCompany() != null)
			company = u.getCompany().getId();
		else
			company = -1;
		firstName = u.getFirstName();
		lastName = u.getLastName();
		city = u.getCity();
		phone = u.getPhone();
		activated = u.isActivated();
		bonusPoints = u.getBonusPoints();
		lastPasswordResetDate = u.getLastPasswordResetDate();
	}
	
	public UserDTO(Optional<User> u) {
		id = u.get().getId();
		email = u.get().getEmail();
		if(u.get().getCompany() != null)
			company = u.get().getCompany().getId();
		else
			company = -1;
		firstName = u.get().getFirstName();
		lastName = u.get().getLastName();
		city = u.get().getCity();
		phone = u.get().getPhone();
		activated = u.get().isActivated();
		bonusPoints = u.get().getBonusPoints();
		lastPasswordResetDate = u.get().getLastPasswordResetDate();
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

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
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

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
		
}
