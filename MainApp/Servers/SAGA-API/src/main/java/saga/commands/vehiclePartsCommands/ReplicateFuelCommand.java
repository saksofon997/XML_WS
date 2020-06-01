package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.CategoryDTO;
import saga.dto.FuelDTO;

public class ReplicateFuelCommand {
    @TargetAggregateIdentifier
    private String fuelAggregateId;

    private Long fuelId;
    private FuelDTO fuelDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateFuelCommand(){

    }

    public ReplicateFuelCommand(String fuelAggregateId, Long fuelId, FuelDTO fuelDTO, TypeOfCommand typeOfCommand) {
        this.fuelAggregateId = fuelAggregateId;
        this.fuelId = fuelId;
        this.fuelDTO = fuelDTO;
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

    public FuelDTO getFuelDTO() {
        return fuelDTO;
    }

    public void setFuelDTO(FuelDTO fuelDTO) {
        this.fuelDTO = fuelDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
