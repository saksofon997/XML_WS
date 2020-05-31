package saga.events;

public class VehicleReplicatedFailedEvent {
    private String vehicleAggregateId;

    private Long vehicleId;
    private String reason;

    public VehicleReplicatedFailedEvent() {
    }

    public VehicleReplicatedFailedEvent(String vehicleAggregateId, Long vehicleId, String reason) {
        this.vehicleAggregateId = vehicleAggregateId;
        this.vehicleId = vehicleId;
        this.reason = reason;
    }

    public String getVehicleAggregateId() {
        return vehicleAggregateId;
    }

    public void setVehicleAggregateId(String vehicleAggregateId) {
        this.vehicleAggregateId = vehicleAggregateId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
