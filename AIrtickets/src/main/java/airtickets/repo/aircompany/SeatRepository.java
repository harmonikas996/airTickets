package airtickets.repo.aircompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	public Seat findById(long id);
	public Seat deleteById(long id);
	public List<Seat> findByFlightId(long id);
}
