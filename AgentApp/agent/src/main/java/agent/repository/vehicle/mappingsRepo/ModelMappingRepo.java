package agent.repository.vehicle.mappingsRepo;

import agent.model.vehicle.Model;
import agent.model.vehicle.mappings.ModelMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelMappingRepo extends JpaRepository<ModelMapping, Long> {
    ModelMapping findByModelAgent(Model model);
}
