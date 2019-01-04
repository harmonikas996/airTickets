package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	public Seat findById(long id);
	public Seat deleteById(long id);
}
