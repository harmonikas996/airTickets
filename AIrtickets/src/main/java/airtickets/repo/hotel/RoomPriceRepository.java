package airtickets.repo.hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.hotel.RoomPrice;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long> {
	public RoomPrice findById(long id);
	public RoomPrice deleteById(long id);
	@Query(value="select * from airtickets.room_price where room_id in \r\n" + 
			"(select id from airtickets.room where hotel_id = ?1)", nativeQuery=true)
	public List<RoomPrice> findByHotel(long id);
	@Query(value="SELECT * FROM airtickets.room_price where (dato_from <= ?2 and dato_to >= ?3 and room_id = ?1);", nativeQuery=true)
	public Optional<RoomPrice> searchRoomPriceForDateRange(long roomId, String datoFrom, String datoTo);
}
