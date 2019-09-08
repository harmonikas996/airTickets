package airtickets.repo.aircompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.aircompany.FlightReservation;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {
	public FlightReservation findById(long id);
	public FlightReservation deleteById(long id);
	@Query(value="select * from airtickets.flight_reservation as fr\r\n" + 
			"						where exists (select * from airtickets.seat as s where s.reservation_id = fr.id and s.client_id = ?1)", nativeQuery=true)
//	@Query(value = "select * from airtickets.flight f where exists \r\n" + 
//			"	(\r\n" + 
//			"	select * from \r\n" + 
//			"		(\r\n" + 
//			"		select flight_id from airtickets.seat s where s.client_id = ?1\r\n" + 
//			"		) as tmp\r\n" + 
//			"	where tmp.flight_id = f.id\r\n" + 
//			"	)", nativeQuery = true)
	public List<FlightReservation> getFlightReservationsByUser(Long id);
}
