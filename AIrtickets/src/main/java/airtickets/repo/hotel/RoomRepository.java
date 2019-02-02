package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	public Room findById(long id);
	public Room deleteById(long id);
}
