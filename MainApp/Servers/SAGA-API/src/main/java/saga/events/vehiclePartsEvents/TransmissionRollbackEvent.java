package saga.events.vehiclePartsEvents;

import saga.commands.TypeOfCommand;

public class TransmissionRollbackEvent {
    private Long transmissionId;
    private TypeOfCommand typeOfCommand;

    public TransmissionRollbackEvent(){

    }

    public TransmissionRollbackEvent(Long transmissionId, TypeOfCommand typeOfCommand) {
        this.transmissionId = transmissionId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
