package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;
import airtickets.model.rentacar.RentACarRating;

public interface RentACarRatingRepository extends JpaRepository<RentACarRating, Long> {
	public RentACarRating findById(long id);
	public RentACarRating deleteById(long id);
}
