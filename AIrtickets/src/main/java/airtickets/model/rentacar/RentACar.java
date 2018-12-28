package airtickets.model.rentacar;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.model.Company;

@Entity
public class RentACar extends Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6928716260507911831L;
	
	@OneToMany(mappedBy = "rentACar", cascade=CascadeType.ALL)// kaskad = cvrsta veza kao kompozicija u pauer diz.
	private List<BranchOffice> branches;	// kod meniTuMeni lejzi loud, ne mora i moze, sta znam
//	@Column
//	private double averageRate;
//	@Column
//	private int numberOfRates;
	@OneToMany(mappedBy = "rentACar", cascade=CascadeType.ALL)
	private List<Vehicle> vehicles;
	@OneToMany(mappedBy = "rentACar", cascade=CascadeType.ALL)
	private List<RentACarRating> ratings;

	public RentACar() {}

	public RentACar(RentACarDTO r) {
		super(r.getId(), r.getName(), r.getAddress(), r.getDescription());
//		this.averageRate = r.getAverageRate();
//		this.numberOfRates = r.getNumberOfRates();
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public List<BranchOffice> getBranches() {
		return branches;
	}

	public List<RentACarRating> getRatings() {
		return ratings;
	}

//	public double getAverageRate() {
//		return averageRate;
//	}
//
//	public void setAverageRate(double averageRate) {
//		this.averageRate = averageRate;
//	}
//
//	public int getNumberOfRates() {
//		return numberOfRates;
//	}
//
//	public void setNumberOfRates(int numberOfRates) {
//		this.numberOfRates = numberOfRates;
//	}

}
