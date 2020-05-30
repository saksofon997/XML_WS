package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rental.model.Bundle;

public interface BundleRepository extends JpaRepository<Bundle, Long> {
}
