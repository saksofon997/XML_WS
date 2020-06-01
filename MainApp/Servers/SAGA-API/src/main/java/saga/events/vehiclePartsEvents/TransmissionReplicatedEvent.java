package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class TransmissionReplicatedEvent {

    private String transmissionAggregateId;
    private TypeOfCommand typeOfCommand;

    public TransmissionReplicatedEvent() {}

    public TransmissionReplicatedEvent(String transmissionAggregateId, TypeOfCommand typeOfCommand) {
        this.transmissionAggregateId = transmissionAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getTransmissionAggregateId() {
        return transmissionAggregateId;
    }

    public void setTransmissionAggregateId(String transmissionAggregateId) {
        this.transmissionAggregateId = transmissionAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
