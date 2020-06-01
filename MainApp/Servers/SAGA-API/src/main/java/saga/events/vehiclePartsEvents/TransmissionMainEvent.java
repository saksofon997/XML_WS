package saga.events.vehiclePartsEvents;

import saga.commands.TypeOfCommand;
import saga.dto.TransmissionDTO;

public class TransmissionMainEvent {
    private Long transmissionId;
    private TransmissionDTO transmissionDTO;
    private TypeOfCommand typeOfCommand;

    public TransmissionMainEvent() {}

    public TransmissionMainEvent(Long transmissionId, TransmissionDTO transmissionDTO, TypeOfCommand typeOfCommand) {
        this.transmissionId = transmissionId;
        this.transmissionDTO = transmissionDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public TransmissionDTO getTransmissionDTO() {
        return transmissionDTO;
    }

    public void setTransmissionDTO(TransmissionDTO fuelDTO) {
        this.transmissionDTO = fuelDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
