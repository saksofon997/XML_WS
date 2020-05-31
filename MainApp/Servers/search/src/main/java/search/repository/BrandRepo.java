package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
