package airtickets.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import airtickets.model.Company;

@Entity
public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854605829282193591L;

	@Column
	private AdminType type;

	@JoinColumn(name = "company_id")
	@ManyToOne
	private Company company;

	public Admin() {}

	public AdminType getType() {
		return type;
	}

	public void setType(AdminType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
