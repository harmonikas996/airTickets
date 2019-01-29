package airtickets.service.user;

import java.util.List;
import java.util.Optional;

import airtickets.dto.user.UserDTO;
import airtickets.model.user.User;

public interface UserService {
	UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll ();
}
