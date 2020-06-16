package agent.repository.vehicle;

import agent.model.vehicle.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
