package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.Vehicle;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
	public Vehicle findById(long id);
}
