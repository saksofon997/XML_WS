package saga.commands.vehiclePartsCommands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.TransmissionDTO;

public class MainTransmissionCommand {
    @TargetAggregateIdentifier
    private Long transmissionId;
    private TransmissionDTO transmissionDTO;
    private TypeOfCommand typeOfCommand;

    public MainTransmissionCommand(){

    }

    public MainTransmissionCommand(Long transmissionId, TransmissionDTO transmissionDTO, TypeOfCommand typeOfCommand) {
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

    public void setTransmissionDTO(TransmissionDTO transmissionDTO) {
        this.transmissionDTO = transmissionDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
