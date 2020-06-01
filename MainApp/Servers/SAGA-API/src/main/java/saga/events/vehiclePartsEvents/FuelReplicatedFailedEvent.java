package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class FuelReplicatedFailedEvent {
    private String fuelAggregateId;

    private Long fuelId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public FuelReplicatedFailedEvent() {}

    public FuelReplicatedFailedEvent(String fuelAggregateId, Long fuelId, String reason, TypeOfCommand typeOfCommand) {
        this.fuelAggregateId = fuelAggregateId;
        this.fuelId = fuelId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getFuelAggregateId() {
        return fuelAggregateId;
    }

    public void setFuelAggregateId(String fuelAggregateId) {
        this.fuelAggregateId = fuelAggregateId;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
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
