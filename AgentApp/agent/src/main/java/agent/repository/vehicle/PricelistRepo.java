package agent.repository.vehicle;

import agent.model.vehicle.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {

    List<Pricelist> findAllByOwnerId(Long ownerId);

    boolean existsByNameAndOwnerId(String name, Long ownerId);
}
