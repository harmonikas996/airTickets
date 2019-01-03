package airtickets.repo.aircompany;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.aircompany.Aircompany;

public interface AircompanyRepository extends JpaRepository<Aircompany, Long> {
	public Aircompany findById(long id);
	public Aircompany deleteById(long id);
}
