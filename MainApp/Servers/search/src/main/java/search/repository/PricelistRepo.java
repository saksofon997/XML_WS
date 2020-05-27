package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Pricelist;

public interface PricelistRepo extends JpaRepository<Pricelist, Long> {
}
