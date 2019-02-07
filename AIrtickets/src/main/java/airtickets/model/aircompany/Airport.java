package airtickets.model.aircompany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import airtickets.dto.aircompany.AirportDTO;

@Entity
public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2104002145711902408L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String city;
	@Column
	private String address;
	
	@OneToMany(mappedBy = "placeFrom", cascade=CascadeType.ALL)
	private List<Flight> froms;
	
	@OneToMany(mappedBy = "placeTo", cascade=CascadeType.ALL)
	private List<Flight> destinations;
	
	@OneToMany(mappedBy = "airport", cascade=CascadeType.ALL)
	private List<Stop> stop;

	public Airport() {}
	
	public Airport(AirportDTO a) {
		
		id = a.getId();
		name = a.getName();
		city = a.getCity();
		address = a.getAddress();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public List<Flight> getFroms() {
		return froms;
	}

	public List<Flight> getDestinations() {
		return destinations;
	}

	public List<Stop> getStop() {
		return stop;
	}
	
}
