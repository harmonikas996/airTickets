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
	private Client initier;
	@JoinColumn(name = "confirmer_id")
	@ManyToOne
	private Client confirmer;
	@Column
	private boolean confirmed;

	public Friendship() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getInitier() {
		return initier;
	}

	public void setInitier(Client initier) {
		this.initier = initier;
	}

	public Client getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(Client confirmer) {
		this.confirmer = confirmer;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

}
