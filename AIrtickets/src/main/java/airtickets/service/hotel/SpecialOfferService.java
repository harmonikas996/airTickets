package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.SpecialOfferDTO;
import airtickets.model.hotel.SpecialOffer;
import airtickets.repo.hotel.SpecialOfferRepository;

public class SpecialOfferService {

	@Autowired
	SpecialOfferRepository specialOfferRepository;
	
	public List<SpecialOfferDTO> getSpecialOffers(){
		List<SpecialOfferDTO> specialOffersDTO = new ArrayList<SpecialOfferDTO>();
		List<SpecialOffer> specialOffers = specialOfferRepository.findAll();
		
		for(SpecialOffer r : specialOffers) {
			SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(r);
			specialOffersDTO.add(specialOfferDTO);
		}
		return specialOffersDTO;
	}
	
	public SpecialOfferDTO getSpecialOffer(long id) {
		SpecialOffer s = specialOfferRepository.findById(id);
		SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(s);
		
		return specialOfferDTO;
	}
	
	public SpecialOfferDTO addSpecialOffer(SpecialOfferDTO specialOfferDTO) {
		SpecialOffer specialOffer = new SpecialOffer(specialOfferDTO);
		specialOfferRepository.save(specialOffer);
		specialOfferDTO.setId(specialOffer.getId());
		
		return specialOfferDTO;
	}
	
	public void deleteSpecialOffer(long id) {
		specialOfferRepository.deleteById(id);
	}
}
