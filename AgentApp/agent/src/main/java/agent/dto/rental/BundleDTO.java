package agent.dto.rental;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class BundleDTO {

    private Long id;
    private String name;
    private Set<RentalDTO> rentals = new HashSet<RentalDTO>();

}
