package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class TransmissionMainFailedEvent {
    private String transmissionAggregateId;
    private Long transmissionId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public TransmissionMainFailedEvent() {}

    public TransmissionMainFailedEvent(String transmissionAggregateId, Long transmissionId, String reason, TypeOfCommand typeOfCommand) {
        this.transmissionAggregateId = transmissionAggregateId;
        this.transmissionId = transmissionId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getTransmissionAggregateId() {
        return transmissionAggregateId;
    }

    public void setTransmissionAggregateId(String transmissionAggregateId) {
        this.transmissionAggregateId = transmissionAggregateId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
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
