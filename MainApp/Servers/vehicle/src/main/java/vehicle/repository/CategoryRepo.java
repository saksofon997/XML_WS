package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
