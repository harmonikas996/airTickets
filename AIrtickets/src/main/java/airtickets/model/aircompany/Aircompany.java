package airtickets.model.aircompany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.model.Company;

@Entity
public class Aircompany extends Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682571857887948968L;
	
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<Flight> flights;
	
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<AircompanyRating> ratings;

	public Aircompany() {}
	
	public Aircompany(AircompanyDTO a) {
		super(a.getId(), a.getName(), a.getAddress(), a.getDescription(), a.getImage());
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public List<AircompanyRating> getRatings() {
		return ratings;
	}

}
