package airtickets.repo.hotel;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	public Room findById(long id);
	public Room deleteById(long id);
	public List<Room> findByHotelId(long id);
	//public Object reservedRoomsForPeriod(long id, LocalDateTime df, LocalDateTime dt);
	@Query(value="select * from airtickets.room where hotel_id=?1 and id not in (\r\n" + 
			"SELECT airtickets.room_reservation.room_id FROM airtickets.hotel_reservation, airtickets.room_reservation\r\n" + 
			"where airtickets.hotel_reservation.id = airtickets.room_reservation.hotel_reservation_id and airtickets.hotel_reservation.hotel_id = ?1\r\n" + 
			"and airtickets.hotel_reservation.date_from <= ?3 and airtickets.hotel_reservation.date_to >= ?2)", nativeQuery=true)
	public List<Room> freeRoomsForPeriod(long id, LocalDateTime df, LocalDateTime dt);
	@Query(value="select * from airtickets.room where id in (\r\n" + 
			"SELECT airtickets.room_reservation.room_id FROM airtickets.hotel_reservation, airtickets.room_reservation\r\n" + 
			"where airtickets.hotel_reservation.id = airtickets.room_reservation.hotel_reservation_id and airtickets.hotel_reservation.hotel_id = ?1\r\n" + 
			"and airtickets.hotel_reservation.date_from <= ?3 and airtickets.hotel_reservation.date_to >= ?2)\r\n" + 
			"", nativeQuery=true)
	public List<Room> reservedRoomsForPeriod(long id, LocalDateTime df, LocalDateTime dt);
	@Query(value="select * from airtickets.room where id in (SELECT airtickets.room.id FROM airtickets.room, airtickets.hotel\r\n" + 
			" where airtickets.room.hotel_id = airtickets.hotel.id and airtickets.hotel.id = ?1 and airtickets.room.type = ?2\r\n" + 
			" and airtickets.room.id not in (select rr.room_id from airtickets.room_reservation rr, airtickets.hotel_reservation hr\r\n" + 
			" where hr.id = rr.hotel_reservation_id and hr.hotel_id = ?1 and hr.date_from <= ?4 \r\n" + 
			" and hr.date_to >= ?3))", nativeQuery=true)
	public List<Room> searchRooms(long id, String type, LocalDateTime df, LocalDateTime dt);
	@Query(value="select * from airtickets.room where hotel_id = ?1", nativeQuery=true)
	public List<Room> getRoomsByHotel(long id);
}
