package airtickets.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import airtickets.model.user.Admin;

@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class Company {

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
	private List<Admin> admins;

	public Company() {}

	public Company(long id, String name, String address, String description) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}

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

	public List<Admin> getAdmins() {
		return admins;
	}

}
