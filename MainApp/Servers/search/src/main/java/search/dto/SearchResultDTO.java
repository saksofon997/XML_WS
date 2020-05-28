package search.dto;

import java.util.List;

public class SearchResultDTO {

    private Long id;
    private List<VehicleDTO> vehicles;

    public SearchResultDTO() {
    }

    public SearchResultDTO(Long id, List<VehicleDTO> vehicles) {
        this.id = id;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
