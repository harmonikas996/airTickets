package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.RoomPrice;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long> {
	public RoomPrice findById(long id);
	public RoomPrice deleteById(long id);
}
