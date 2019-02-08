package airtickets.repo.rentacar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airtickets.model.rentacar.BranchOffice;
import airtickets.model.rentacar.Vehicle;

public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Long> {
	public BranchOffice findById(long id);
	public BranchOffice deleteById(long id);
	public List<String> findAllCities();
	@Query(value="SELECT * FROM branch_office where rentacar_id = ?1", nativeQuery = true)
	public List<BranchOffice> findByRentACarId(long id);
}
