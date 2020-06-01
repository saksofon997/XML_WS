package saga.events;

import saga.commands.TypeOfCommand;

public class VehicleReplicatedEvent {
    private String vehicleAggregateId;
    private TypeOfCommand typeOfCommand;

    public VehicleReplicatedEvent() {
    }

    public VehicleReplicatedEvent(String vehicleAggregateId, TypeOfCommand typeOfCommand) {
        this.vehicleAggregateId = vehicleAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getVehicleAggregateId() {
        return vehicleAggregateId;
    }

    public void setVehicleAggregateId(String vehicleAggregateId) {
        this.vehicleAggregateId = vehicleAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
