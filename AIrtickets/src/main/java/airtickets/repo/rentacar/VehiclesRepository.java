package airtickets.repo.rentacar;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.Vehicle;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
	public Vehicle findById(long id);
	public Vehicle deleteById(long id);
	@Query(value="SELECT * FROM vehicle where rentacar_id = ?1", nativeQuery = true)
	public List<Vehicle> findByRentACarId(long id);
	
	@Query(value="SELECT * FROM vehicle where id in (select vehicle.id from vehicle, "
			+ "rentacar where vehicle.rentacar_id=rentacar.id and rentacar.id = ?1 "
			+ "and vehicle.type = ?2 and vehicle.number_of_seats >= ?3 "
			+ "and vehicle.price_per_day >= ?4 "
			+ "and vehicle.price_per_day <= ?5 "
			+ "and vehicle.id "
			+ "not in (select vehicle.id from vehicle, "
			+ "car_reservation, rentacar "
			+ "where vehicle.id=car_reservation.vehicle_id and vehicle.rentacar_id=rentacar.id "
			+ "and rentacar.id = ?1 and car_reservation.date_from <= ?7 "
			+ "and car_reservation.date_to >= ?6))", nativeQuery = true)
	//@Query(value="SELECT * FROM vehicle where id in (select vehicle.id from vehicle, rentacar where vehicle.rentacar_id=rentacar.id and rentacar.id = ?1 and vehicle.type = ?2)", nativeQuery = true)
	public List<Vehicle> searchVehicles(long rentacarId, int type, int passangers, double lowerPrice, double upperPrice, LocalDateTime from, LocalDateTime to);
	@Query(value="SELECT * FROM vehicle where vehicle.rentacar_id = ?1 and id not in\r\n" + 
			"(select vehicle.id  \r\n" + 
			" FROM car_reservation, vehicle, rentacar  \r\n" + 
			"where rentacar.id = vehicle.rentacar_id and car_reservation.vehicle_id = vehicle.id and rentacar.id = ?1 \r\n" + 
			"and date_from >= ?2 and  date_from <= ?3)", nativeQuery=true)
	public List<Vehicle> freeVehiclesForPeriod(long rcrId, LocalDateTime from, LocalDateTime to);
	@Query(value="SELECT * from vehicle\r\n" + 
			"where id in \r\n" + 
			"(select vehicle.id\r\n" + 
			" FROM car_reservation, vehicle, rentacar\r\n" + 
			"where rentacar.id = vehicle.rentacar_id and car_reservation.vehicle_id = vehicle.id and rentacar.id = ?1\r\n" + 
			"and date_from >= ?2 and  date_from <= ?3)", nativeQuery=true)
	public List<Vehicle> reservedVehiclesForPeriod(long rcrId, LocalDateTime from, LocalDateTime to);
}
