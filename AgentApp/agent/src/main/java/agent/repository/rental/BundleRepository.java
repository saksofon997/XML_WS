package agent.repository.rental;

import agent.model.rental.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundleRepository extends JpaRepository<Bundle, Long> {
}
