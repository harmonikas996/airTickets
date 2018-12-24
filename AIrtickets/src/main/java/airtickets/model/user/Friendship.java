package airtickets.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Friendship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4617822700426549028L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "initier_id")
	@ManyToOne
	private User initier;
	@JoinColumn(name = "confirmer_id")
	@ManyToOne
	private User confirmer;
	@Column
	private boolean confirmed;

	public Friendship() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getInitier() {
		return initier;
	}

	public void setInitier(User initier) {
		this.initier = initier;
	}

	public User getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(User confirmer) {
		this.confirmer = confirmer;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

}
