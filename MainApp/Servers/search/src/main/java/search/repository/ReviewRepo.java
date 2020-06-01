package search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.model.Review;
import search.model.ReviewStatus;
import search.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByStatusEquals(ReviewStatus pending);

    List<Review> findByVehicle(Vehicle vehicle);

    Optional<Review> findByIdAndVehicle(Long id, Vehicle vehicle);
}
