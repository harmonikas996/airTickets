package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
	public Airport findById(long id);
	public Airport deleteById(long id);
}
