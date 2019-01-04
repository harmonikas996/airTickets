package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.FlightRating;

public interface FlightRatingRepository extends JpaRepository<FlightRating, Long> {
	public FlightRating findById(long id);
	public FlightRating deleteById(long id);
}
