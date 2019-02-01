package airtickets.controller.hotel;

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

import airtickets.dto.hotel.SpecialOfferDTO;
import airtickets.service.hotel.SpecialOfferService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/specialOffers")
public class SpecialOfferController {

	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping("/all")
	public List<SpecialOfferDTO> getAllSpecialOffers(){
		return specialOfferService.getSpecialOffers();
	}
	
	@GetMapping("/{id}")
	public SpecialOfferDTO getById(@PathVariable Long id){
		return specialOfferService.getSpecialOffer(id);
	}
	
	@PostMapping("/new")
	public SpecialOfferDTO addSpecialOffer(@RequestBody SpecialOfferDTO specialOffer) {
		return specialOfferService.addSpecialOffer(specialOffer);
	}
	
	@PutMapping("{id}/update")
	public SpecialOfferDTO updateSpecialOffer(@RequestBody SpecialOfferDTO specialOffer, @PathVariable Long id) {
		specialOffer.setId(id);
		return specialOfferService.addSpecialOffer(specialOffer);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteSpecialOffer(@PathVariable Long id) {
		specialOfferService.deleteSpecialOffer(id);
	}
}
