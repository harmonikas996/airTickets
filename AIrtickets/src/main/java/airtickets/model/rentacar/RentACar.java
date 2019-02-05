package airtickets.model.rentacar;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.model.Company;
//String name, String location, String timeBegin, String timeEnd
//@NamedQuery(name="RentACar.searchRentACars", query="select rc from RentACar rc where rc.name = ?1")

@NamedQuery(name="RentACar.searchBranches", query="select bo\r\n" + 
		"from BranchOffice bo\r\n" + 
		"where bo.id in\r\n" + 
		"(SELECT bo.id\r\n" + 
		"FROM RentACar rcOld, BranchOffice bo, Vehicle v \r\n" + 
		"where rcOld.id=bo.rentACar.id and rcOld.id=v.rentACar.id\r\n" + 
		"	 and (rcOld.name like ?1 and bo.city like ?2)\r\n" + 
		"     and v.id not in (\r\n" + 
		"     \r\n" + 
		"		select cr.vehicle.id from CarReservation cr\r\n" + 
		"        where cr.dateFrom <= ?4\r\n" +
		"     and cr.dateTo >= ?3\r\n" + 
		"     )\r\n" + 
		")")
@NamedQuery(name="RentACar.searchRentACars", query="select rc\r\n" + 
		"from RentACar rc\r\n" + 
		"where rc.id in\r\n" + 
		"(SELECT rcOld.id\r\n" + 
		"FROM RentACar rcOld, BranchOffice bo, Vehicle v \r\n" + 
		"where rcOld.id=bo.rentACar.id and rc.id=v.rentACar.id\r\n" + 
		"	 and (rc.name like ?1 and bo.city like ?2)\r\n" + 
		"     and v.id not in (\r\n" + 
		"     \r\n" + 
		"		select cr.vehicle.id from CarReservation cr\r\n" + 
		"        where cr.dateFrom <= ?4\r\n" +
		"     and cr.dateTo >= ?3\r\n" + 
		"     )\r\n" + 
		")")
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
