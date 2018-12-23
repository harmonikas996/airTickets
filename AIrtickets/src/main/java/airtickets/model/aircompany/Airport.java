package airtickets.model.aircompany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2104002145711902408L;
	@Id
	private long id;
	@Column
	private String city;
	@Column
	private String address;

	public Airport() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
