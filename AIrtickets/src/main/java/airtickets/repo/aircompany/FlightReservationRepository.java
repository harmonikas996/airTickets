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
	public List<FlightReservation> getFlightReservationsByUser(Long id);
}
