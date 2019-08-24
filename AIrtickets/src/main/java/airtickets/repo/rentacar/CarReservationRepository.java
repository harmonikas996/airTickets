package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.CarReservation;

public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
	public CarReservation findById(long id);
	public CarReservation deleteById(long id);
	public List<CarReservation> findByVehicleId(long id);
	@Query(value="select * from\r\n" + 
			"(select * from airtickets.car_reservation\r\n" + 
			"	where not exists (\r\n" + 
			"		select * from airtickets.flight_reservation where airtickets.flight_reservation.car_reservation_id = airtickets.car_reservation.id\r\n" + 
			"	)\r\n" + 
			") as tmp\r\n" + 
			"where tmp.vehicle_id in (select airtickets.vehicle.id from airtickets.vehicle where airtickets.vehicle.rentacar_id = ?1)", nativeQuery=true)
	public List<CarReservation> getQuickCarReservationsByCompanyId(long id);
	@Query(value="		select * from airtickets.car_reservation cr\r\n" + 
			"			where exists (\r\n" + 
			"				select * from\r\n" + 
			"					(\r\n" + 
			"					select * from airtickets.flight_reservation as fr\r\n" + 
			"						where exists (select * from airtickets.seat as s where s.reservation_id = fr.id and s.client_id = ?1)\r\n" + 
			"					) as tmp\r\n" + 
			"				where car_reservation_id = cr.id\r\n" + 
			"				)", nativeQuery=true)
	public List<CarReservation> getCarReservationsByUser(Long id);
}
