package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.AmenityReservation;

public interface AmenityReservationRepository extends JpaRepository<AmenityReservation, Long> {
	public AmenityReservation findById(long id);
	public AmenityReservation deleteById(long id);
}
