package airtickets.repo.aircompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.aircompany.AircompanyRating;
import airtickets.model.aircompany.FlightRating;

public interface AircompanyRatingRepository extends JpaRepository<AircompanyRating, Long> {
	public AircompanyRating findById(long id);
	public AircompanyRating deleteById(long id);
	public AircompanyRating findBycompanyId(long id);
	@Query(value = "SELECT * FROM airtickets.aircompany_rating where company_id = ?1", nativeQuery = true)
	public List<AircompanyRating> findAllById(Long companyId);
}
