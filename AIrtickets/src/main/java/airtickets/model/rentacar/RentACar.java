package airtickets.model.rentacar;

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
public class RentACar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6928716260507911831L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@OneToMany(mappedBy = "rentACar", cascade=CascadeType.ALL)
	private List<Income> incomes;
	@OneToMany(mappedBy = "rentACar", cascade=CascadeType.ALL)// kaskad = cvrsta veza kao kompozicija u pauer diz.
	private List<BranchOffice> branches;	// kod meniTuMeni lejzi loud, ne mora i moze, sta znam
	@Column
	double averageRate;
	@Column
	int numberOfRates;

	public RentACar() {}

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

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public int getNumberOfRates() {
		return numberOfRates;
	}

	public void setNumberOfRates(int numberOfRates) {
		this.numberOfRates = numberOfRates;
	}
	
}
