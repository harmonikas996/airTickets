package airtickets.service.hotel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import airtickets.dto.hotel.DiscountDTO;
import airtickets.model.hotel.Discount;
import airtickets.repo.hotel.DiscountRepository;

@Service
public class DiscountService {
	
	@Autowired
	DiscountRepository discountRepository;
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<DiscountDTO> getDiscounts(){
		List<DiscountDTO> discountsDTO = new ArrayList<DiscountDTO>();
		List<Discount> discounts = discountRepository.findAll();
		
		for(Discount d : discounts) {
			DiscountDTO discountDTO = new DiscountDTO(d);
			discountsDTO.add(discountDTO);
		}
		return discountsDTO;
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public DiscountDTO getDiscount(long id) {
		Discount d = discountRepository.findById(id);
		DiscountDTO amenityReservation = new DiscountDTO(d);
		
		return amenityReservation;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public DiscountDTO addDiscount(DiscountDTO discountDTO) {
		Discount discount = new Discount(discountDTO);
		discountRepository.save(discount);
		discountDTO.setId(discount.getId());
		
		return discountDTO;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public void deleteDiscount(long id) {
		discountRepository.deleteById(id);
	}
}
