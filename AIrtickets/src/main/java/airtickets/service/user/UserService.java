package airtickets.service.user;

import java.util.List;
import java.util.Optional;

import airtickets.model.user.User;

public interface UserService {
	Optional<User> findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}
