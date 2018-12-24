package airtickets.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.model.aircompany.Seat;

@Entity
public class Invitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270216844960649769L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "inviter_id")
	@ManyToOne
	private User inviter;
	@JoinColumn(name = "invited_id")
	@ManyToOne
	private User invited;
	@JoinColumn(name = "seat_id")
	@ManyToOne
	private Seat seat;
	@Column
	private boolean accepted;

	public Invitation() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getInviter() {
		return inviter;
	}

	public void setInviter(User inviter) {
		this.inviter = inviter;
	}

	public User getInvited() {
		return invited;
	}

	public void setInvited(User invited) {
		this.invited = invited;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

}
