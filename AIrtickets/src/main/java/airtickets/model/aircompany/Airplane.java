package airtickets.model.aircompany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Airplane implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7302980740377362198L;
	@Id
	private long id;
	@Column
	private int capacity;
	@Column
	private String name;
	@Column
	private String brand;
	@Column
	private String model;
	@JoinColumn(name = "company_id")
	@ManyToOne
	private Aircompany company;

	public Airplane() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Aircompany getCompany() {
		return company;
	}

	public void setCompany(Aircompany company) {
		this.company = company;
	}

}
