package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
