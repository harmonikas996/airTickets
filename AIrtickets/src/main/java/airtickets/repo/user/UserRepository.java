package airtickets.repo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.user.Friendship;
import airtickets.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail( String email );
	Optional<User> findById(Long id);
	// samo ime ili samo prezime
	@Query(value = "select * from airtickets.user where airtickets.user.type = 4 and (airtickets.user.first_name = ?1 or airtickets.user.last_name = ?1)", nativeQuery = true)
	public List<User> getUsers(String name);
	// ime i prezime
	@Query(value = "select * from airtickets.user where airtickets.user.type = 4 and airtickets.user.first_name = ?1 and airtickets.user.last_name = ?2", nativeQuery = true)
	public List<User> getUsers(String name1, String name2);
}

