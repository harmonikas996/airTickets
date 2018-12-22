package airtickets.service.rentacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.RentACarDTO;
import airtickets.model.rentacar.RentACar;
import airtickets.repo.rentacar.RentACarRepository;

@Service
public class RentACarService {

	@Autowired
	RentACarRepository rentACarRepository;

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
}
