package airtickets.dto.user;

import airtickets.model.user.Friendship;

public class FriendShipDTO {
	
	private long id;
	private long initier;
	private long confirmer;
	private boolean confirmed;
	
	public FriendShipDTO() {}
	
	public FriendShipDTO(Friendship f) {
		this.id = f.getId();
		this.initier = f.getInitier().getId();
		this.confirmer = f.getConfirmer().getId();
		this.confirmed = f.isConfirmed();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getInitier() {
		return initier;
	}
	public void setInitier(long initier) {
		this.initier = initier;
	}
	public long getConfirmer() {
		return confirmer;
	}
	public void setConfirmer(long confirmer) {
		this.confirmer = confirmer;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	
}
