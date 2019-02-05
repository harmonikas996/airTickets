package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.Vehicle;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
	public Vehicle findById(long id);
	public Vehicle deleteById(long id);
	public List<Vehicle> findByRentACarId(long id, String name);
//	Query(,value="SELECT *\r\n" + 
//			"FROM airtickets.vehicle\r\n" + 
//			"where id in (\r\n" + 
//			"\r\n" + 
//			"	select v.id\r\n" + 
//			"    from airtickets.vehicle v, airtickets.rentacar r\r\n" + 
//			"    where v.rentacar_id=r.id\r\n" + 
//			"    and r.id = ?1 and v.type = ?2 and v.number_of_seats >= ?3 and v.price_per_day <= ?4 and v.price_per_day >= ?5\r\n" + 
//			"    and v.id not in (\r\n" + 
//			"		\r\n" + 
//			"        select c.id\r\n" + 
//			"        from airtickets.vehicle c, airtickets.car_reservation cr, airtickets.rentacar rc\r\n" + 
//			"        where c.id=cr.vehicle_id and c.rentacar_id=rc.id and rc.id = ?1\r\n" + 
//			"        and cr.date_from <= ?7 and cr.date_to >= ?6\r\n" + 
//			"    )\r\n" + 
//			")")
	public List<Vehicle> searchVehicles();
}
