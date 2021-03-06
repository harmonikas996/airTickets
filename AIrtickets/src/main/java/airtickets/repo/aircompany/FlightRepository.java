package airtickets.repo.aircompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.aircompany.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	public Flight findById(long id);
	public Flight deleteById(long id);
	@Query(value = "select * from flight where from_id regexp ?1 and destination_id regexp ?2 and time_begin regexp ?3", nativeQuery = true)
	public List<Flight> searchFlights(String placeFromId, String placeToId, String date);
	@Query(value = "select * from flight where company_id = ?1", nativeQuery = true)
	public List<Flight> getFlightsByUser(Long id);
	@Query(value = "select * from flight where from_id regexp ?1 and destination_id regexp ?2 and time_begin regexp ?3 and company_id = ?4", nativeQuery = true)
	public List<Flight> searchFlightsByCompany(String pf, String pt, String date, Long companyId);
}
