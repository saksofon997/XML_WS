package saga.events.manualOccupancyEvents;

import saga.dto.VehicleOccupancyDTO;

public class ManualOccupancyEvent {
    private Long occupancyId;
    private VehicleOccupancyDTO occupancyDTO;
    private Long vehicleId;

    public ManualOccupancyEvent() {

    }

    public ManualOccupancyEvent(Long occupancyId, VehicleOccupancyDTO occupancyDTO, Long vehicleId) {
        this.occupancyId = occupancyId;
        this.occupancyDTO = occupancyDTO;
        this.vehicleId = vehicleId;
    }

    public Long getOccupancyId() {
        return occupancyId;
    }

    public void setOccupancyId(Long occupancyId) {
        this.occupancyId = occupancyId;
    }

    public VehicleOccupancyDTO getOccupancyDTO() {
        return occupancyDTO;
    }

    public void setOccupancyDTO(VehicleOccupancyDTO occupancyDTO) {
        this.occupancyDTO = occupancyDTO;
    }


    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
