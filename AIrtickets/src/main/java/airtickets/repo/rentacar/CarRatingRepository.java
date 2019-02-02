package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;
import airtickets.model.rentacar.CarRating;

public interface CarRatingRepository extends JpaRepository<CarRating, Long> {
	public CarRating findById(long id);
	public CarRating deleteById(long id);
}
