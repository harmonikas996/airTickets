package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
	public Amenity findById(long id);
	public Amenity deleteById(long id);
}
