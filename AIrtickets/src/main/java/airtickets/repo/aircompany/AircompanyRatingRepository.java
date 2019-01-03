package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.AircompanyRating;

public interface AircompanyRatingRepository extends JpaRepository<AircompanyRating, Long> {
	public AircompanyRating findById(long id);
	public AircompanyRating deleteById(long id);
}
