package airtickets.service.aircompany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.AircompanyDTO;
import airtickets.model.aircompany.Aircompany;
import airtickets.repo.aircompany.AircompanyRepository;

@Service
public class AircompanyService {
	
	@Autowired
	AircompanyRepository airCompanyRepository;
	
	public List<AircompanyDTO> getAircompanies(){
		List<AircompanyDTO> aircompanies = new ArrayList<AircompanyDTO>();
		List<Aircompany> air = airCompanyRepository.findAll();
		
		for(Aircompany a : air) {
			AircompanyDTO aircompany = new AircompanyDTO(a);
			aircompanies.add(aircompany);
		}
		return aircompanies;
	}
	
	public AircompanyDTO getAircompany(long id) {
		Aircompany a = airCompanyRepository.findById(id);
		AircompanyDTO aircompany = new AircompanyDTO(a);
		
		return aircompany;
	}
	
	public AircompanyDTO addAircompany(AircompanyDTO aircompanyDTO) {
		Aircompany aircompany = new Aircompany(aircompanyDTO);
		airCompanyRepository.save(aircompany);
		aircompanyDTO.setId(aircompany.getId());
		
		return aircompanyDTO;
	}
	
	public void deleteAircompany(long id) {
		airCompanyRepository.deleteById(id);
	}

	public List<Double> monthyIncome(long aircId, int year) {
		
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
			
			Double d = airCompanyRepository.incomeForPeriodRegular(aircId, from, to);
			Double dd = airCompanyRepository.incomeForPeriodDiscount(aircId, from, to);
			
			if (d == null && dd == null)
				incomes.add(0.0);
			else if (d == null)
				incomes.add(dd);
			else
				incomes.add(d+dd);
		}
		
		return incomes;
	}

	public List<Double> weeklyIncome(long aircId, int year) {
		
		List<Double> incomes = new ArrayList<>();
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse(year + "-01-08T00:00:00");
		
		for (int i = 1; i <= 52; i++) {
			Double d = airCompanyRepository.incomeForPeriodRegular(aircId, from, to);
			Double dd = airCompanyRepository.incomeForPeriodDiscount(aircId, from, to);
			
			if (d == null && dd == null)
				incomes.add(0.0);
			else if (d == null)
				incomes.add(dd);
			else
				incomes.add(d+dd);
			
			from = from.plusDays(7);
			to = to.plusDays(7);
		}
		to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		Double d = airCompanyRepository.incomeForPeriodRegular(aircId, from, to);
		Double dd = airCompanyRepository.incomeForPeriodDiscount(aircId, from, to);
		
		if (d == null && dd == null)
			incomes.add(0.0);
		else if (d == null)
			incomes.add(dd);
		else
			incomes.add(d+dd);
		
		return incomes;
	}

	public double yearlyIncome(long aircId, int year) {
		
		LocalDateTime from = LocalDateTime.parse(year + "-01-01T00:00:00");
		LocalDateTime to = LocalDateTime.parse((year+1) + "-01-01T00:00:00");
		
		Double d = airCompanyRepository.incomeForPeriodRegular(aircId, from, to);
		Double dd = airCompanyRepository.incomeForPeriodDiscount(aircId, from, to);
		
		if (d == null && dd == null)
			return 0.0;
		else if (d == null)
			return dd;
		else
			return d+dd;
	}
}
