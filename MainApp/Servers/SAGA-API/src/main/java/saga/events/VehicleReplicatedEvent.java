package saga.events;

public class VehicleReplicatedEvent {
    private String vehicleAggregateId;

    public VehicleReplicatedEvent() {
    }

    public VehicleReplicatedEvent(String vehicleAggregateId) {
        this.vehicleAggregateId = vehicleAggregateId;
    }

    public String getVehicleAggregateId() {
        return vehicleAggregateId;
    }

    public void setVehicleAggregateId(String vehicleAggregateId) {
        this.vehicleAggregateId = vehicleAggregateId;
    }
}
