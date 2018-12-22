package airtickets.repo.rentacar;

import org.springframework.data.jpa.repository.JpaRepository;

import airtickets.model.rentacar.RentACar;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {
	public RentACar findById(long id);
	public RentACar deleteById(long id);
}
