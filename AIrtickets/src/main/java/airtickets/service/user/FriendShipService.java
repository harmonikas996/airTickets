package airtickets.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import airtickets.dto.user.FriendShipDTO;
import airtickets.model.user.Friendship;
import airtickets.repo.user.FriendShipRepository;

@Service
public class FriendShipService {
	
	@Autowired
	private FriendShipRepository friendShipRepository;
	
	public List<FriendShipDTO> getFriendShips() {
		
		List<FriendShipDTO> friendships = new ArrayList<FriendShipDTO>();
		
		for (Friendship f  : friendShipRepository.findAll()) {
			FriendShipDTO friendShip = new FriendShipDTO(f);
			friendships.add(friendShip);
 		}
		return friendships;
	}
	
	public FriendShipDTO getFriendShip(long id) {
		Friendship f  = friendShipRepository.findById(id);
		FriendShipDTO friendShip = new FriendShipDTO(f);
		return friendShip;
	}

	public List<FriendShipDTO> getFriends(long id) {
		List<FriendShipDTO> friendships = new ArrayList<FriendShipDTO>();
		
		for (Friendship f  : friendShipRepository.getFriends(id)) {
			FriendShipDTO friendShip = new FriendShipDTO(f);
			friendships.add(friendShip);
 		}
		return friendships;
	}
	
	public List<FriendShipDTO> getFriendRequest(long id) {
		List<FriendShipDTO> friendships = new ArrayList<FriendShipDTO>();
		
		for (Friendship f  : friendShipRepository.getFriendRequests(id)) {
			FriendShipDTO friendShip = new FriendShipDTO(f);
			friendships.add(friendShip);
 		}
		return friendships;
	}
	
	public FriendShipDTO addFriendShip(FriendShipDTO friendShipDTO) {
		Friendship friendShip = new Friendship(friendShipDTO);
		friendShipRepository.save(friendShip);
		friendShipDTO.setId(friendShip.getId());
		return friendShipDTO;
	}
	
	public void deleteFriendShip(long id) {
		friendShipRepository.deleteById(id);
	}
	
}
