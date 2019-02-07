package airtickets.repo.aircompany;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.aircompany.Aircompany;

public interface AircompanyRepository extends JpaRepository<Aircompany, Long> {
	public Aircompany findById(long id);
	public Aircompany deleteById(long id);
	@Query(value="SELECT sum(airtickets.flight.price) FROM airtickets.flight, airtickets.seat\r\n" + 
			"where airtickets.flight.id = airtickets.seat.flight_id\r\n" + 
			"and company_id = ?1 and airtickets.seat.price is null\r\n" + 
			"and time_begin >= ?2 and time_end <= ?3" + 
			"", nativeQuery=true)
	public Double incomeForPeriodRegular(long hotId, LocalDateTime from, LocalDateTime to);
	@Query(value="SELECT sum(airtickets.seat.price) FROM airtickets.flight, airtickets.seat\r\n" + 
			"where airtickets.flight.id = airtickets.seat.flight_id\r\n" + 
			"and company_id = ?1 and airtickets.seat.price is not null\r\n" + 
			"and time_begin >= ?2 and time_end <= ?3" + 
			"", nativeQuery=true)
	public Double incomeForPeriodDiscount(long hotId, LocalDateTime from, LocalDateTime to);
}
