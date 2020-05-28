package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Fuel;

public interface FuelRepo extends JpaRepository<Fuel, Long> {
}
