package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.Vehicle;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
	public Vehicle findById(long id);
	public Vehicle deleteById(long id);
	public List<Vehicle> findByRentACarId(long id, String name);
}
