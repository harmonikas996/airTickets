package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	public RoomReservation findById(long id);
	public RoomReservation deleteById(long id);
}
