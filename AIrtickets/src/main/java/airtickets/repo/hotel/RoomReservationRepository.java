package airtickets.repo.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	public RoomReservation findById(long id);
	public RoomReservation deleteById(long id);
	@Query(value="select airtickets.room_reservation.id, hotel_reservation_id, room_id from\r\n" + 
			"(select * from\r\n" + 
			"	(\r\n" + 
			"	select * from airtickets.hotel_reservation\r\n" + 
			"		where not exists (\r\n" + 
			"			select * from airtickets.flight_reservation where airtickets.flight_reservation.hotel_reservation_id = airtickets.hotel_reservation.id\r\n" + 
			"		)\r\n" + 
			"	) as tmp\r\n" + 
			"	where tmp.hotel_id = ?1\r\n" + 
			") as tmp2\r\n" + 
			"join\r\n" + 
			"airtickets.room_reservation on tmp2.id = airtickets.room_reservation.hotel_reservation_id", nativeQuery=true)
	public List<RoomReservation> getQuickRoomReservationsByCompanyId(long id);
	@Query(value="		select * from airtickets.room_reservation rr\r\n" + 
			"			where exists (\r\n" + 
			"				select * from\r\n" + 
			"					(\r\n" + 
			"					select * from airtickets.flight_reservation as fr\r\n" + 
			"						where exists (select * from airtickets.seat as s where s.reservation_id = fr.id and s.client_id = ?1)\r\n" + 
			"					) as tmp\r\n" + 
			"				where hotel_reservation_id = rr.hotel_reservation_id\r\n" + 
			"				)", nativeQuery=true)
	public List<RoomReservation> getRoomReservationsByUser(Long id);
}
