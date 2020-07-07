package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Pricelist;

import java.util.List;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {

    List<Pricelist> findAllByOwnerId(Long ownerId);

    boolean existsByNameAndOwnerId(String name, Long ownerId);
}
