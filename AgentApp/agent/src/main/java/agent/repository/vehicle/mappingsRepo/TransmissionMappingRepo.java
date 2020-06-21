package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.Transmission;
import agent.model.vehicle.mappings.TransmissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionMappingRepo extends JpaRepository<TransmissionMapping, Long> {
    TransmissionMapping findByTransmissionAgent(Transmission transmission);
}
