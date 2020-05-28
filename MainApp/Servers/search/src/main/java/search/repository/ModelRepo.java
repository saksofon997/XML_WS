package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Model;

public interface ModelRepo extends JpaRepository<Model, Long> {
}
