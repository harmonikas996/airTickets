package airtickets.repo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.hotel.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	public SpecialOffer findById(long id);
	public SpecialOffer deleteById(long id);
}
