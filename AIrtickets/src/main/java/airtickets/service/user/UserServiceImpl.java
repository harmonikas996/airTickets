package airtickets.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import airtickets.dto.user.UserDTO;
import airtickets.model.user.Authority;
import airtickets.model.user.User;
import airtickets.repo.user.AuthorityRepository;
import airtickets.repo.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public UserDTO findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByEmail(username);
		UserDTO user = new UserDTO(u);
		return user;
	}
	
	@Override
	public User findUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	public UserDTO findById(Long id) throws AccessDeniedException {
		Optional<User> u = userRepository.findById(id);
		UserDTO user = new UserDTO(u);
		return user;
	}

	public List<UserDTO> findAll() throws AccessDeniedException {
		
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		List<User> users = userRepository.findAll();
		
		for(User u : users) {
			UserDTO userDTO = new UserDTO(u);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}
	
	public List<UserDTO> findClients() throws AccessDeniedException {
		
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		List<User> users = userRepository.findAll();
		
		for(User u : users) {
			if (u.getType().equals("client")) {
				UserDTO userDTO = new UserDTO(u);
				usersDTO.add(userDTO);
			}
		}
		return usersDTO;
	}
	
	public UserDTO addUser(UserDTO userDTO) {
		
		User user = new User(userDTO);
		userRepository.save(user);
		userDTO.setId(user.getId());
		
		return userDTO;
	}

	@Override
	public List<UserDTO> searchUsers(String name) {
		
		String[] tokens = name.split("_");
		
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		List<User> users = null;
		if(tokens.length > 1)
			users = userRepository.getUsers(tokens[0], tokens[1]);	
		else	
			users = userRepository.getUsers(tokens[0]);
		
		for(User u : users) {
			UserDTO userDTO = new UserDTO(u);
			usersDTO.add(userDTO);
		}
		return usersDTO;
		
	}

	@Override
	public UserDTO addAuthority(UserDTO user) {
		User u = this.findUserByUsername(user.getEmail());
		
		if(u.getType().equals("client") && !user.getType().equals("client")) {
			// admin ga dodeljuje kompaniji (proglasava ga za admina kompanije)
			
			Optional<Authority> authority = authorityRepository.findByName(user.getType());
	        
	        if(authority.isPresent()) {
	        	authority.get().getUsers().add(u);
	        	return this.addUser(user);
	        }
		}
		return new UserDTO();
	}
}
