package agent.repository.vehicle;

import agent.model.vehicle.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepo extends JpaRepository<Transmission, Long> {
    boolean existsByName(String name);
}
