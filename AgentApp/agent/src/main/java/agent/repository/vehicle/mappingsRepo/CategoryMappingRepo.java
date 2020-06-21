package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.Category;
import agent.model.vehicle.mappings.CategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMappingRepo extends JpaRepository<CategoryMapping, Long> {
    CategoryMapping findByCategoryAgent(Category category);
}
