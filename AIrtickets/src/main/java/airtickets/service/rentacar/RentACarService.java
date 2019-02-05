package airtickets.service.rentacar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.user.UserDTO;
import airtickets.model.rentacar.RentACar;
import airtickets.model.user.User;
import airtickets.repo.rentacar.RentACarRepository;
import airtickets.repo.user.UserRepository;

@Service
public class RentACarService {

	@Autowired
	RentACarRepository rentACarRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<RentACarDTO> getRentACars() {
		List<RentACarDTO> rentACars = new ArrayList<RentACarDTO>();
		
		for (RentACar r  : rentACarRepository.findAll()) {
			RentACarDTO rentACar = new RentACarDTO(r);
			rentACars.add(rentACar);
 		}
		return rentACars;
	}

	public RentACarDTO getRentACar(long id) {
		RentACar r  = rentACarRepository.findById(id);
		RentACarDTO rentACar = new RentACarDTO(r);
		return rentACar;
	}

	public RentACarDTO addRentACar(RentACarDTO rentACarDTO) {
		RentACar rentACar = new RentACar(rentACarDTO);
		rentACarRepository.save(rentACar);
		rentACarDTO.setId(rentACar.getId());
		return rentACarDTO;
	}

	public void deleteRentACar(long id) {
		rentACarRepository.deleteById(id);
	}
	
	public RentACarDTO getRentACarByAdmin(String adminUsername) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());
		RentACar r  = rentACarRepository.findById(user.getCompany().getId());
		RentACarDTO rentACar = new RentACarDTO(r);
		
		return rentACar;
	}
	
	public List<RentACarDTO> searchRentACars(String name, String location, String timeBegin, String timeEnd) {
		
		LocalDateTime ldtFrom = LocalDateTime.parse(timeBegin);
		LocalDateTime ldtTo = LocalDateTime.parse(timeEnd);
		
		if(name.equals(" ") || name == null)
			name = "%%";
		if(location.equals(" ") || location == null)
			location = "%%";
			
		
		List<RentACarDTO> rentACars = new ArrayList<RentACarDTO>();
		
		for (RentACar r  : rentACarRepository.searchRentACars(name, location, ldtFrom, ldtTo)) {
			RentACarDTO rentACar = new RentACarDTO(r);
			rentACars.add(rentACar);
 		}
		return rentACars;
	}
}
