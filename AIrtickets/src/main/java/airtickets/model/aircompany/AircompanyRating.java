package airtickets.model.aircompany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.model.user.User;

@Entity
public class AircompanyRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2912631457491804537L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "company_id")
	@ManyToOne
	private Aircompany company;
	@Column
	private int rating;

	public AircompanyRating() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Aircompany getCompany() {
		return company;
	}

	public void setCompany(Aircompany company) {
		this.company = company;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
