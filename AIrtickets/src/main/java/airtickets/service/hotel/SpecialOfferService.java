package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.SpecialOfferDTO;
import airtickets.model.hotel.SpecialOffer;
import airtickets.repo.hotel.SpecialOfferRepository;

@Service
public class SpecialOfferService {

	@Autowired
	SpecialOfferRepository specialOfferRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<SpecialOfferDTO> getSpecialOffers(){
		List<SpecialOfferDTO> specialOffersDTO = new ArrayList<SpecialOfferDTO>();
		List<SpecialOffer> specialOffers = specialOfferRepository.findAll();
		
		for(SpecialOffer r : specialOffers) {
			SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(r);
			specialOffersDTO.add(specialOfferDTO);
		}
		return specialOffersDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public SpecialOfferDTO getSpecialOffer(long id) {
		SpecialOffer s = specialOfferRepository.findById(id);
		SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(s);
		
		return specialOfferDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public SpecialOfferDTO addSpecialOffer(SpecialOfferDTO specialOfferDTO) {
		SpecialOffer specialOffer = new SpecialOffer(specialOfferDTO);
		specialOfferRepository.save(specialOffer);
		specialOfferDTO.setId(specialOffer.getId());
		
		return specialOfferDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteSpecialOffer(long id) {
		specialOfferRepository.deleteById(id);
	}
}
