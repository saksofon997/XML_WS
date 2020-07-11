package agent.repository.rental.mappingsRepo;

import agent.model.rental.Rental;
import agent.model.rental.mappings.RentalMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalMappingRepo extends JpaRepository<RentalMapping, Long> {
    RentalMapping findByRentalBackId(Long id);

    RentalMapping findByRentalAgentId(Rental rental);
}
