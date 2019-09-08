package airtickets.repo.aircompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.aircompany.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	public Seat findById(long id);
	public Seat deleteById(long id);
	public List<Seat> findByFlightId(long id);
	@Query(value = "select * from airtickets.seat s where\r\n" + 
			"	(\r\n" + 
			"    select * from\r\n" + 
			"		(\r\n" + 
			"        select id from airtickets.flight f where company_id = ?1\r\n" + 
			"        ) as tmp\r\n" + 
			"	where tmp.id = s.flight_id and price > 0  and first_name is null and last_name is null\r\n" + 
			"	)", nativeQuery = true)
	public List<Seat> getQuickSeatsByCompanyId(long id);
}
