package agent.repository.rental;

import agent.model.rental.RentalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalReportRepository  extends JpaRepository<RentalReport, Long> {
}
