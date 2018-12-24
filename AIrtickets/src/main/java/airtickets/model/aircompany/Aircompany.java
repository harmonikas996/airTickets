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

@Entity
public class Aircompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682571857887948968L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<Flight> flights;
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<Airplane> airplanes;
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<AircompanyRating> ratings;

	public Aircompany() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public List<Airplane> getAirplanes() {
		return airplanes;
	}

}
