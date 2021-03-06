package airtickets.model.rentacar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import airtickets.dto.rentacar.BranchOfficeDTO;

@NamedQuery(name="BranchOffice.findAllCities", query="select distinct bo.city from BranchOffice bo")
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
	@Column
	private String city;

	public BranchOffice() {}

	public BranchOffice(BranchOfficeDTO b) {
		this.id = b.getId();
		this.rentACar = new RentACar();
		this.rentACar.setId(b.getRentACarId());
		this.address = b.getAddress();
		this.city = b.getCity();
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
