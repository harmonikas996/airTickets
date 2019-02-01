package airtickets.dto.hotel;

import airtickets.model.hotel.SpecialOffer;

public class SpecialOfferDTO {
	private long id;
	private double percentage;
	private String title;
	
	public SpecialOfferDTO() {}
	
	public SpecialOfferDTO(SpecialOffer specialOffer) {
		id = specialOffer.getId();
		percentage = specialOffer.getPercentage();
		title = specialOffer.getTitle();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
