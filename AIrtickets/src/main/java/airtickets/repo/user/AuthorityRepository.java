package airtickets.repo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.user.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Optional<Authority> findById(Long id);
	Optional<Authority> findByName(String name);
}
