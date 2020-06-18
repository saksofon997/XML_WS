package agent.model.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class Role {
    @Id
    @SequenceGenerator(name="roles_id_seq",sequenceName="roles_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roles_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id", referencedColumnName = "id"))
    private Set<Permission> permissions;

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else if (name != null) {
            return name.hashCode();
        }

        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof Role))
            return false;

        Role anotherRole = (Role) another;

        return anotherRole.id != null && (anotherRole.id == this.id);
    }

    @Override
    public String toString() {
        return name;
    }
}
