package airtickets.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.user.Friendship;

public interface FriendShipRepository extends JpaRepository<Friendship, Long> {
	public Friendship findById(long id);
	@Query(value = "select * from airtickets.friendship where airtickets.friendship.confirmed = true and (airtickets.friendship.confirmer_id = ?1 or airtickets.friendship.initier_id = ?1)", nativeQuery = true)
	public List<Friendship> getFriends(long id);
	@Query(value = "select * from airtickets.friendship where airtickets.friendship.confirmed = false and airtickets.friendship.confirmer_id = ?1", nativeQuery = true)
	public List<Friendship> getFriendRequests(long id);	
}
