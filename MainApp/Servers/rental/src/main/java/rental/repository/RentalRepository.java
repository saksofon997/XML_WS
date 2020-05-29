package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rental.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
