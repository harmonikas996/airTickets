package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	public Discount findById(long id);
	public Discount deleteById(long id);
}
