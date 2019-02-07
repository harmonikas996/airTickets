package airtickets.repo.rentacar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.RentACar;
import airtickets.model.rentacar.Vehicle;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {
	public RentACar findById(long id);
	public RentACar deleteById(long id);
	public List<RentACar> searchRentACars(String name, String location, LocalDateTime timeBegin, LocalDateTime timeEnd);
	public List<BranchOffice> searchBranches(String name, String location, LocalDateTime timeBegin, LocalDateTime timeEnd);
	@Query(value="SELECT sum(airtickets.car_reservation.price) FROM airtickets.car_reservation, airtickets.vehicle, airtickets.rentacar\r\n" + 
			"where airtickets.rentacar.id = airtickets.vehicle.rentacar_id and airtickets.car_reservation.vehicle_id = airtickets.vehicle.id and airtickets.rentacar.id = ?1\r\n" + 
			"and date_from >= ?2 and  date_from < ?3\r\n" + 
			"group by airtickets.rentacar.id\r\n" + 
			"", nativeQuery=true)
	public Double incomeForPeriod(long rcrId, LocalDateTime from, LocalDateTime to);
}
