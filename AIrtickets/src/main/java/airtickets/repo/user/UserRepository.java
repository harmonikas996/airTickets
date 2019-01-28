package airtickets.repo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail( String email );
	Optional<User> findById(Long id);
}

