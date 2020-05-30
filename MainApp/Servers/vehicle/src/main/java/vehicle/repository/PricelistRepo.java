package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Pricelist;

import java.util.List;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {
    boolean existsByName(String name);

    boolean existsByOwnerId(Long ownerId);

    List<Pricelist> findAllByOwnerId(Long ownerId);
}
