package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class FuelReplicatedEvent {

    private String fuelAggregateId;
    private TypeOfCommand typeOfCommand;

    public FuelReplicatedEvent() {}

    public FuelReplicatedEvent(String fuelAggregateId, TypeOfCommand typeOfCommand) {
        this.fuelAggregateId = fuelAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getFuelAggregateId() {
        return fuelAggregateId;
    }

    public void setFuelAggregateId(String fuelAggregateId) {
        this.fuelAggregateId = fuelAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
