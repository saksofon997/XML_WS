package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Model;

public interface ModelRepo extends JpaRepository<Model, Long> {
}
