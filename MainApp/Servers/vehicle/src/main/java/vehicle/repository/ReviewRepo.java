package vehicle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Review;
import vehicle.model.ReviewStatus;
import vehicle.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByStatusEquals(ReviewStatus pending);

    List<Review> findByVehicleAndStatusEquals(Vehicle vehicle, ReviewStatus published);

    List<Review> findByVehicle(Vehicle vehicle);

    Optional<Review> findByIdAndVehicle(Long id, Vehicle vehicle);

    Page<Review> findAllByStatusEquals(Pageable page, ReviewStatus pending);
}
