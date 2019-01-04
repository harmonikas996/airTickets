package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.HotelReservation;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {
	public HotelReservation findById(long id);
	public HotelReservation deleteById(long id);
}
