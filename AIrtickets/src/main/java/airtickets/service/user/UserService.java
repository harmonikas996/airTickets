package airtickets.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import airtickets.dto.user.UserDTO;
import airtickets.model.user.User;

public interface UserService {
	UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll ();
    List<UserDTO> findClients ();
    UserDTO addUser(UserDTO user);
    List<UserDTO> searchUsers(String name1);
    UserDTO addAuthority(UserDTO user1);
	User findUserByUsername(String username) throws UsernameNotFoundException;
}
