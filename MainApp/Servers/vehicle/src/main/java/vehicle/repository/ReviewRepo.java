package vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
