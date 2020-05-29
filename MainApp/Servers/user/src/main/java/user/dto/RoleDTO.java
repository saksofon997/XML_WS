package user.dto;

import lombok.Data;
import user.model.Role;

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
