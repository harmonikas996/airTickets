package airtickets.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import airtickets.model.rentacar.BranchOffice;

@Entity
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1054486061799605884L;
	@Id
	private long id;
	private String name;
	@OneToMany(mappedBy = "city", cascade=CascadeType.ALL)
	private List<BranchOffice> branches;

	public City() {}

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

}
