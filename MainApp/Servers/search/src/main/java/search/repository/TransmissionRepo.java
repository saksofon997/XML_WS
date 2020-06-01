package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Transmission;

public interface TransmissionRepo extends JpaRepository<Transmission, Long> {
    boolean existsByName(String name);
}
