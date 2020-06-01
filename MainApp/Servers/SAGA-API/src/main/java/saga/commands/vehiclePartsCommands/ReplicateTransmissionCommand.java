package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.TransmissionDTO;

public class ReplicateTransmissionCommand {
    @TargetAggregateIdentifier
    private String transmissionAggregateId;

    private Long transmissionId;
    private TransmissionDTO transmissionDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateTransmissionCommand(){

    }

    public ReplicateTransmissionCommand(String transmissionAggregateId, Long transmissionId, TransmissionDTO transmissionDTO, TypeOfCommand typeOfCommand) {
        this.transmissionAggregateId = transmissionAggregateId;
        this.transmissionId = transmissionId;
        this.transmissionDTO = transmissionDTO;
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
