package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.RentACarRating;

public interface RentACarRatingRepository extends JpaRepository<RentACarRating, Long> {
	public RentACarRating findById(long id);
	public RentACarRating deleteById(long id);
	public RentACarRating findByRentACarId(long id);
	@Query(value = "SELECT * FROM airtickets.rentacar_rating where rentacar_id = ?1", nativeQuery = true)
	public List<RentACarRating> findAllById(Long rentACarId);
}
