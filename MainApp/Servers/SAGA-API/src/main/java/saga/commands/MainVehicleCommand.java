package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.VehicleDTO;

public class MainVehicleCommand {
    @TargetAggregateIdentifier
    private Long vehicleId;
    private VehicleDTO vehicleDTO;
    private TypeOfCommand typeOfCommand;

    public MainVehicleCommand() {
    }

    public MainVehicleCommand(Long vehicleId, VehicleDTO vehicleDTO, TypeOfCommand typeOfCommand) {
        this.vehicleId = vehicleId;
        this.vehicleDTO = vehicleDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
