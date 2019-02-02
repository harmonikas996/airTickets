package airtickets.dto.aircompany;

import airtickets.model.aircompany.Aircompany;

public class AircompanyDTO {
	
	//pr
	private long id;
	private String name;
	private String address;
	private String description;
	
	public AircompanyDTO() {}
	
	public AircompanyDTO(Aircompany aircompany) {
		id = aircompany.getId();
		name = aircompany.getName();
		address = aircompany.getAddress();
		description = aircompany.getDescription();
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

}
