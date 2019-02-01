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

import airtickets.dto.hotel.DiscountDTO;
import airtickets.service.hotel.DiscountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@GetMapping("/all")
	public List<DiscountDTO> getAllDiscounts(){
		return discountService.getDiscounts();
	}
	
	@GetMapping("/{id}")
	public DiscountDTO getById(@PathVariable Long id){
		return discountService.getDiscount(id);
	}
	
	@PostMapping("/new")
	public DiscountDTO addDiscount(@RequestBody DiscountDTO discount) {
		return discountService.addDiscount(discount);
	}
	
	@PutMapping("{id}/update")
	public DiscountDTO updateDiscount(@RequestBody DiscountDTO discount, @PathVariable Long id) {
		discount.setId(id);
		return discountService.addDiscount(discount);
	}
	
	@DeleteMapping("{id}/delete")
	public void deleteDiscount(@PathVariable Long id) {
		discountService.deleteDiscount(id);
	}
}
