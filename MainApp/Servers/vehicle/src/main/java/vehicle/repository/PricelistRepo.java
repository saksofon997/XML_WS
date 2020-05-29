package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Pricelist;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {
}
