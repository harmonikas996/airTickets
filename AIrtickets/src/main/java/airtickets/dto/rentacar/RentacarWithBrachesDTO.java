package airtickets.dto.rentacar;

import java.util.ArrayList;
import java.util.List;

public class RentacarWithBrachesDTO {

	private RentACarDTO rentacar;
	private List<BranchOfficeDTO> branches;

	public RentacarWithBrachesDTO() {
		branches = new ArrayList<>();
	}

	public RentACarDTO getRentacar() {
		return rentacar;
	}

	public void setRentacar(RentACarDTO rentacar) {
		this.rentacar = rentacar;
	}

	public List<BranchOfficeDTO> getBranches() {
		return branches;
	}

}
