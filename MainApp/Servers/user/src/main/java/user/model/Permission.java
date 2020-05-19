package user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class Permission {
    @Id
    @SequenceGenerator(name="permissions_id_seq",sequenceName="permissions_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissions_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST)
    private Set<Role> roles;
}
