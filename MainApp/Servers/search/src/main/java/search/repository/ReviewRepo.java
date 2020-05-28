package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
