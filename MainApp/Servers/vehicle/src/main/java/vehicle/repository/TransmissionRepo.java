package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Transmission;

public interface TransmissionRepo extends JpaRepository<Transmission, Long> {
    boolean existsByName(String name);
}
