package saga.events.vehiclePartsEvents;

import saga.commands.TypeOfCommand;

public class FuelRollbackEvent {
    private Long fuelId;
    private TypeOfCommand typeOfCommand;

    public FuelRollbackEvent(){

    }

    public FuelRollbackEvent(Long fuelId, TypeOfCommand typeOfCommand) {
        this.fuelId = fuelId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
