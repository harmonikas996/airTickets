package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	public Hotel findById(long id);
	public Hotel deleteById(long id);
}
