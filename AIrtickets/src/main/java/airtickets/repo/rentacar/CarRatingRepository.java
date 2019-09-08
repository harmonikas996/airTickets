package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.CarRating;
import airtickets.model.rentacar.RentACarRating;

public interface CarRatingRepository extends JpaRepository<CarRating, Long> {
	public CarRating findById(long id);
	public CarRating deleteById(long id);
	public CarRating findByVehicleId(long id);
	@Query(value = "SELECT * FROM airtickets.car_rating where vehicle_id = ?1", nativeQuery = true)
	public List<CarRating> findAllById(Long vehicleId);
}
