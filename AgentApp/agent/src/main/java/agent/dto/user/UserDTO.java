package agent.dto.user;

import agent.model.user.Company;
import agent.model.user.Role;
import agent.model.user.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private boolean enabled;
    private String company;
    private Set<RoleDTO> roles = new HashSet<RoleDTO>();

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.state = user.getState();
        this.phoneNumber = user.getPhoneNumber();
        this.company = user.getCompany();
        for (Role role: user.getRoles()) {
            this.roles.add(new RoleDTO(role));
        }
    }
}
