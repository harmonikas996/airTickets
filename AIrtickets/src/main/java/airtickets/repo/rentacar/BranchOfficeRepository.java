package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.BranchOffice;

public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Long> {
	public BranchOffice findById(long id);
	public BranchOffice deleteById(long id);
}
