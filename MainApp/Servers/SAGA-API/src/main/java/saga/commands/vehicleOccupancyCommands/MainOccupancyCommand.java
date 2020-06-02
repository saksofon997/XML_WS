package saga.commands.vehicleOccupancyCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.VehicleOccupancyDTO;

public class MainOccupancyCommand {
    @TargetAggregateIdentifier
    private Long vehicleOccupancyId;
    private Long vehicleId;
    private VehicleOccupancyDTO occupancyDTO;
    private TypeOfCommand typeOfCommand;

    public MainOccupancyCommand() {
    }

    public MainOccupancyCommand(Long vehicleOccupancyId, Long vehicleId, VehicleOccupancyDTO occupancyDTO, TypeOfCommand typeOfCommand) {
        this.vehicleOccupancyId = vehicleOccupancyId;
        this.vehicleId = vehicleId;
        this.occupancyDTO = occupancyDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleOccupancyId() {
        return vehicleOccupancyId;
    }

    public void setVehicleOccupancyId(Long vehicleOccupancyId) {
        this.vehicleOccupancyId = vehicleOccupancyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleOccupancyDTO getOccupancyDTO() {
        return occupancyDTO;
    }

    public void setOccupancyDTO(VehicleOccupancyDTO occupancyDTO) {
        this.occupancyDTO = occupancyDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
