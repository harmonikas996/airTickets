package airtickets.repo.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.HotelRating;
import airtickets.model.hotel.RoomRating;

public interface RoomRatingRepository extends JpaRepository<RoomRating, Long> {
	public RoomRating findById(long id);
	public RoomRating deleteById(long id);
	public RoomRating findByroomId(long id);
	@Query(value = "SELECT * FROM airtickets.room_rating where room_id = ?1", nativeQuery = true)
	public List<RoomRating> findAllById(Long companyId);
}
