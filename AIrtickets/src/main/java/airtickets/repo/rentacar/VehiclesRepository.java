package airtickets.repo.rentacar;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.CarType;
import airtickets.model.rentacar.Vehicle;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
	public Vehicle findById(long id);
	public Vehicle deleteById(long id);
	public List<Vehicle> findByRentACarId(long id, String name);
	
	@Query(value="SELECT * FROM vehicle where id in (select vehicle.id from vehicle, rentacar where vehicle.rentacar_id=rentacar.id and rentacar.id = ?1 and vehicle.type = ?2 and vehicle.number_of_seats >= ?3 and vehicle.price_per_day >= ?4 and vehicle.price_per_day <= ?5 and vehicle.id not in (select vehicle.id from vehicle, car_reservation, rentacar where vehicle.id=car_reservation.vehicle_id and vehicle.rentacar_id=rentacar.id and rentacar.id = ?1 and car_reservation.date_from <= ?7 and car_reservation.date_to >= ?6))", nativeQuery = true)
	//@Query(value="SELECT * FROM vehicle where id in (select vehicle.id from vehicle, rentacar where vehicle.rentacar_id=rentacar.id and rentacar.id = ?1 and vehicle.type = ?2)", nativeQuery = true)
	public List<Vehicle> searchVehicles(long rentacarId, int type, int passangers, double lowerPrice, double upperPrice, LocalDateTime from, LocalDateTime to);
}
