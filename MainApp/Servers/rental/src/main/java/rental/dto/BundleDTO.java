package rental.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class BundleDTO {

    private Long id;
    private String name;
    private Set<RentalDTO> rentals = new HashSet<RentalDTO>();

    public BundleDTO(Long id, String name, Set<RentalDTO> rentals) {
        this.id = id;
        this.name = name;
        this.rentals = rentals;
    }

    public BundleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalDTO> rentals) {
        this.rentals = rentals;
    }

    @Override
    public String toString() {
        return "BundleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BundleDTO bundleDTO = (BundleDTO) o;
        return Objects.equals(getId(), bundleDTO.getId()) &&
                Objects.equals(getName(), bundleDTO.getName()) &&
                Objects.equals(getRentals(), bundleDTO.getRentals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRentals());
    }
}
