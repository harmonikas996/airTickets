package airtickets.model.rentacar;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Income implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8500598991252167296L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "rentacar_id")
	@ManyToOne
	private RentACar rentACar;
	@Column
	private LocalDate date;
	@Column
	private double balance;

	public Income() {}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
