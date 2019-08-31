package airtickets.repo.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
	public Amenity findById(long id);
	public Amenity deleteById(long id);
	@Query(value="SELECT * FROM airtickets.amenity where hotel_id = ?1", nativeQuery=true)
	public List<Amenity> getAmenitiesByHotel(Long id);
}
