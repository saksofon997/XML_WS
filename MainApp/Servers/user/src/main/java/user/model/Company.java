package user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class Company {
    @Id
    @SequenceGenerator(name="company_id_seq",sequenceName="company_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="company_id_seq")
    private Long id;

    @Column(name = "cid", nullable = false, unique = true)
    private String cid;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> agents;
}
