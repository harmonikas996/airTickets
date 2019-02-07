package airtickets.service.rentacar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import airtickets.dto.rentacar.BranchOfficeDTO;
import airtickets.dto.rentacar.RentACarDTO;
import airtickets.dto.rentacar.RentacarWithBrachesDTO;
import airtickets.dto.rentacar.VehicleDTO;
import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.RentACar;
import airtickets.model.rentacar.Vehicle;
import airtickets.model.user.User;
import airtickets.repo.rentacar.RentACarRepository;
import airtickets.repo.rentacar.VehiclesRepository;
import airtickets.repo.user.UserRepository;

@Service
public class RentACarService {

	@Autowired
	RentACarRepository rentACarRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehiclesRepository vehicleRepository;

	public List<RentACarDTO> getRentACars() {
		List<RentACarDTO> rentACars = new ArrayList<RentACarDTO>();
		
		for (RentACar r  : rentACarRepository.findAll()) {
			RentACarDTO rentACar = new RentACarDTO(r);
			rentACars.add(rentACar);
 		}
		return rentACars;
	}

	public RentACarDTO getRentACar(long id) {
		RentACar r  = rentACarRepository.findById(id);
		RentACarDTO rentACar = new RentACarDTO(r);
		return rentACar;
	}

	public RentACarDTO addRentACar(RentACarDTO rentACarDTO) {
		RentACar rentACar = new RentACar(rentACarDTO);
		rentACarRepository.save(rentACar);
		rentACarDTO.setId(rentACar.getId());
		return rentACarDTO;
	}

	public void deleteRentACar(long id) {
		rentACarRepository.deleteById(id);
	}
	
	public RentACarDTO getRentACarByAdmin(String adminUsername) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());
		RentACar r  = rentACarRepository.findById(user.getCompany().getId());
		RentACarDTO rentACar = new RentACarDTO(r);
		
		return rentACar;
	}
	
	public List<RentacarWithBrachesDTO> searchRentACars(String name, String location, String timeBegin, String timeEnd) {
		
		LocalDateTime ldtFrom = LocalDateTime.parse(timeBegin);
		LocalDateTime ldtTo = LocalDateTime.parse(timeEnd);
		
		if(name.equals(" ") || name == null)
			name = "%%";
		if(location.equals(" ") || location == null)
			location = "%%";
		
		List<RentacarWithBrachesDTO> rentACars = new ArrayList<>();
		
		for (RentACar r  : rentACarRepository.searchRentACars(name, location, ldtFrom, ldtTo)) {
			RentACarDTO rentACar = new RentACarDTO(r);
			RentacarWithBrachesDTO rwb = new RentacarWithBrachesDTO();
			rwb.setRentacar(rentACar);
			rentACars.add(rwb);
 		}
		
		for (BranchOffice r  : rentACarRepository.searchBranches(name, location, ldtFrom, ldtTo)) {
			BranchOfficeDTO bo = new BranchOfficeDTO(r);
			for (RentacarWithBrachesDTO rc : rentACars) {
				if (rc.getRentacar().getId() == bo.getRentACarId()) {
					rc.getBranches().add(bo);
					break;
				}
			}
 		}
		
		return rentACars;
	}

	public List<Double> monthyIncome(long rcrId, int year) {
		
		List<Double> incomes = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			
			LocalDateTime from;
			LocalDateTime to;
			
			if (i < 9) {				
				from = LocalDateTime.parse(year + "-0" + i + "-01T00:00:00");
				to = LocalDateTime.parse(year + "-0" + (i+1) + "-01T00:00:00");
			}
			else if (i == 9) {
				from = LocalDateTime.parse(year + "-09-01T00:00:00");
				to = LocalDateTime.parse(year + "-10-01T00:00:00");
			}
			else if (i < 12) {
				from = LocalDateTime.parse(year + "-" + i + "-01T00:00:00");
				to = LocalDateTime.parse(year + "-" + (i+1) + "-01T00:00:00");
			}
			else {
				from = LocalDateTime.parse(year + "-12-01T00:00:00");
				to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
			}
			
			Double d = rentACarRepository.incomeForMonth(rcrId, from, to);
			
			incomes.add(d!=null ? d : 0);
		}
		
		return incomes;
	}
	
	public List<VehicleDTO> freeVehiclesForPeriod(long id, String from, String to) {
		
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<VehicleDTO> veh = new ArrayList<>();
		
		for (Vehicle v : vehicleRepository.freeVehiclesForPeriod(id, df, dt)) {
			veh.add(new VehicleDTO(v));
		}
		
		return veh;
	}

	public List<VehicleDTO> reservedVehiclesForPeriod(long id, String from, String to) {
		
		LocalDateTime df = LocalDateTime.parse(from);
		LocalDateTime dt = LocalDateTime.parse(to);
		
		List<VehicleDTO> veh = new ArrayList<>();
		
		for (Vehicle v : vehicleRepository.reservedVehiclesForPeriod(id, df, dt)) {
			veh.add(new VehicleDTO(v));
		}
		
		return veh;
	}

	public List<Double> weeklyIncome(long rcrId, int year) {
		
		List<Double> incomes = new ArrayList<>();
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse(year + "-01-08T00:00:00");
		
		for (int i = 1; i <= 52; i++) {
			Double d = rentACarRepository.incomeForMonth(rcrId, from, to);
			incomes.add(d!=null ? d : 0);
			
			from = from.plusDays(7);
			to = to.plusDays(7);
		}
		to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		Double d = rentACarRepository.incomeForMonth(rcrId, from, to);
		incomes.add(d!=null ? d : 0);
		
		return incomes;
	}

	public double yearlyIncome(long rcrId, int year) {
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		
		Double income = rentACarRepository.incomeForMonth(rcrId, from, to);
		
		if (income == null)
			income = 0.0;
		
		return income;
	}
}
