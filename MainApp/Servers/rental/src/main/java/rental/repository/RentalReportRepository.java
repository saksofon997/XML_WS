package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rental.model.RentalReport;

public interface RentalReportRepository  extends JpaRepository<RentalReport, Long> {
}
