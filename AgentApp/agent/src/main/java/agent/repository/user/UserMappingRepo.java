package agent.repository.user;

import agent.model.user.User;
import agent.model.user.mappings.UserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMappingRepo extends JpaRepository<UserMapping, Long> {
    UserMapping findByUserAgentId(User user);
}
