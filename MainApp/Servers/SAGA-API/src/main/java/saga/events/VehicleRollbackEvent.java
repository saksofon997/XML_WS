package saga.events;

public class VehicleRollbackEvent {
    private Long vehicleId;

    public VehicleRollbackEvent() {
    }

    public VehicleRollbackEvent(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
