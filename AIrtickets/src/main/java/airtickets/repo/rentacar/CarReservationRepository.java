package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.CarReservation;

public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
	public CarReservation findById(long id);
	public CarReservation deleteById(long id);
	public List<CarReservation> findByVehicleId(long id);
}
