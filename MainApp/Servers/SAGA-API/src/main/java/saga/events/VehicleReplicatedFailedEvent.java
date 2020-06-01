package saga.events;

import saga.commands.TypeOfCommand;

public class VehicleReplicatedFailedEvent {
    private String vehicleAggregateId;

    private Long vehicleId;
    private String reason;
    private TypeOfCommand typeOfCommand;
    public VehicleReplicatedFailedEvent() {
    }

    public VehicleReplicatedFailedEvent(String vehicleAggregateId, Long vehicleId, String reason, TypeOfCommand typeOfCommand) {
        this.vehicleAggregateId = vehicleAggregateId;
        this.vehicleId = vehicleId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
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

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
