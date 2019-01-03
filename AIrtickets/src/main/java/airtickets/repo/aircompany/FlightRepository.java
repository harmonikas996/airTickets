package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	public Flight findById(long id);
	public Flight deleteById(long id);
}
