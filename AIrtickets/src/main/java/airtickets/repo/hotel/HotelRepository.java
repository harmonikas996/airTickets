package airtickets.repo.hotel;

import java.time.LocalDateTime;
import java.util.List;

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
	@Query(value="select * from airtickets.hotel where id in\r\n" + 
			"(SELECT airtickets.room.hotel_id FROM airtickets.room, airtickets.hotel\r\n" + 
			" where airtickets.room.hotel_id = airtickets.hotel.id\r\n" + 
			" and (airtickets.hotel.name like ?1 and airtickets.hotel.city like ?2)  \r\n" + 
			" and airtickets.room.id not in (select rr.room_id from airtickets.room_reservation rr, airtickets.hotel_reservation hr\r\n" + 
			" where hr.id = rr.hotel_reservation_id and hr.date_from <= ?4 \r\n" + 
			" and hr.date_to >= ?3))", nativeQuery=true)
	public List<Hotel> searchHotels(String name, String location, LocalDateTime timeBegin, LocalDateTime timeEnd);
}
