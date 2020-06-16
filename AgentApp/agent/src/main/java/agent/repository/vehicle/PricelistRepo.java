package agent.repository.vehicle;

import agent.model.vehicle.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {
    boolean existsByName(String name);

    boolean existsByOwnerId(Long ownerId);

    List<Pricelist> findAllByOwnerId(Long ownerId);
}