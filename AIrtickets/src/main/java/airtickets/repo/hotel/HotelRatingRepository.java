package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.HotelRating;

public interface HotelRatingRepository extends JpaRepository<HotelRating, Long> {
	public HotelRating findById(long id);
	public HotelRating deleteById(long id);
}
