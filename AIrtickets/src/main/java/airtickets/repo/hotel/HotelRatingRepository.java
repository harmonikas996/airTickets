package airtickets.repo.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.HotelRating;
import airtickets.model.hotel.RoomRating;

public interface HotelRatingRepository extends JpaRepository<HotelRating, Long> {
	public HotelRating findById(long id);
	public HotelRating deleteById(long id);
	public HotelRating findByhotelId(long id);
	@Query(value = "SELECT * FROM airtickets.hotel_rating where hotel_id = ?1", nativeQuery = true)
	public List<HotelRating> findAllById(Long companyId);
	
}
