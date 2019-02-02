package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.RoomRating;

public interface RoomRatingRepository extends JpaRepository<RoomRating, Long> {
	public RoomRating findById(long id);
	public RoomRating deleteById(long id);
}
