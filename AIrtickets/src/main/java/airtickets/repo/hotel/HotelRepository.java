package airtickets.repo.hotel;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	public Hotel findById(long id);
	public Hotel deleteById(long id);
	@Query(value="SELECT sum(airtickets.hotel_reservation.price) FROM airtickets.hotel_reservation\r\n" + 
			"where airtickets.hotel_reservation.hotel_id = ?1\r\n" + 
			"and date_from >= ?2 and  date_from < ?3\r\n" + 
			"group by airtickets.hotel_reservation.hotel_id\r\n" + 
			"", nativeQuery=true)
	public Double incomeForPeriod(long hotId, LocalDateTime from, LocalDateTime to);
}
