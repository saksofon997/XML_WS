package agent.dto.user;

import agent.model.user.Role;
import lombok.Data;

@Data
public class RoleDTO {

    private long id;
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
