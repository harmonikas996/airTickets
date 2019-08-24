package airtickets.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airtickets.dto.user.FriendShipDTO;
import airtickets.dto.user.UserDTO;
import airtickets.service.user.FriendShipService;
import airtickets.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/friendship")
public class FriendshipController {
	
	@Autowired
	private FriendShipService friendShipService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<FriendShipDTO> getAllFriendShips(){
		return friendShipService.getFriendShips();
	}
	
	@GetMapping("/{id}")
	public FriendShipDTO getById(@PathVariable Long id){
		return friendShipService.getFriendShip(id);
	}
	
	@GetMapping("/friends/{username}")
	public List<FriendShipDTO> getFriends(@PathVariable String username){
		UserDTO u = this.userService.findByUsername(username);
		return friendShipService.getFriends(u.getId());
	}
	
	@GetMapping("/request/{username}")
	public List<FriendShipDTO> getFriendRequests(@PathVariable String username){
		UserDTO u = this.userService.findByUsername(username);
		return friendShipService.getFriendRequest(u.getId());
	}
	
	@PostMapping("/new")
	public FriendShipDTO addFriendShip(@RequestBody FriendShipDTO friendShip) {
		return friendShipService.addFriendShip(friendShip);
	}
	
	@PutMapping("{id}/update")
	public FriendShipDTO updateFriendShip(@RequestBody FriendShipDTO friendShip, @PathVariable Long id) {
		friendShip.setId(id);
		return friendShipService.addFriendShip(friendShip);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteFriendShip(@PathVariable Long id) {
		friendShipService.deleteFriendShip(id);
	}
}
