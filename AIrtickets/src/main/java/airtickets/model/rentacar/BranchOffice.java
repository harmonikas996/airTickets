package airtickets.model.rentacar;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import airtickets.model.City;

@Entity
public class BranchOffice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7503889470312209144L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "rentacar_id")
	@ManyToOne
	private RentACar rentACar;
	@Column
	private String address;
	@OneToMany(mappedBy = "branch", cascade=CascadeType.ALL)
	private List<Vehicle> vehicles;
	@JoinColumn(name = "city_id")
	@ManyToOne
	private City city;

	public BranchOffice() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
