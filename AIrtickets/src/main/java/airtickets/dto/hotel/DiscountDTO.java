package airtickets.dto.hotel;

import airtickets.model.hotel.Discount;

public class DiscountDTO {

	private long id;
	private long amenityId;
	private long offerId;
	
	public DiscountDTO() {}
	
	public DiscountDTO(Discount discount) {
		id = discount.getId();
		amenityId = discount.getAmenity().getId();
		offerId = discount.getOffer().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(long amenityId) {
		this.amenityId = amenityId;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
}
