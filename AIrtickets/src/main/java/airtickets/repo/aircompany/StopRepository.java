package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {
	public Stop findById(long id);
	public Stop deleteById(long id);
}
