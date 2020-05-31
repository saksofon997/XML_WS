package saga.events;


import saga.dto.VehicleDTO;

public class VehicleCreatedEvent {
    private Long vehicleId;
    private VehicleDTO vehicleDTO;

    public VehicleCreatedEvent() {
    }

    public VehicleCreatedEvent(Long brandId, VehicleDTO brandDTO) {
        this.vehicleId = brandId;
        this.vehicleDTO = brandDTO;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long brandId) {
        this.vehicleId = brandId;
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }
}
