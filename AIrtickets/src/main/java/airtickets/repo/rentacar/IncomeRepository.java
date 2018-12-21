package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
