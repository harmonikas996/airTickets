package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import airtickets.dto.hotel.DiscountDTO;
import airtickets.model.hotel.Discount;
import airtickets.repo.hotel.DiscountRepository;

public class DiscountService {
	
	@Autowired
	DiscountRepository discountRepository;
	
	public List<DiscountDTO> getDiscounts(){
		List<DiscountDTO> discountsDTO = new ArrayList<DiscountDTO>();
		List<Discount> discounts = discountRepository.findAll();
		
		for(Discount d : discounts) {
			DiscountDTO discountDTO = new DiscountDTO(d);
			discountsDTO.add(discountDTO);
		}
		return discountsDTO;
	}
	
	public DiscountDTO getDiscount(long id) {
		Discount d = discountRepository.findById(id);
		DiscountDTO amenityReservation = new DiscountDTO(d);
		
		return amenityReservation;
	}
	
	public DiscountDTO addDiscount(DiscountDTO discountDTO) {
		Discount discount = new Discount(discountDTO);
		discountRepository.save(discount);
		discountDTO.setId(discount.getId());
		
		return discountDTO;
	}
	
	public void deleteDiscount(long id) {
		discountRepository.deleteById(id);
	}
}
