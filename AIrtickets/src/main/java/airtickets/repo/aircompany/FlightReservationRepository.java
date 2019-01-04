package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.FlightReservation;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {
	public FlightReservation findById(long id);
	public FlightReservation deleteById(long id);
}
