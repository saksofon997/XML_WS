package saga.events;

import saga.commands.TypeOfCommand;

public class VehicleRollbackEvent {
    private Long vehicleId;
    private TypeOfCommand typeOfCommand;

    public VehicleRollbackEvent() {
    }

    public VehicleRollbackEvent(Long vehicleId, TypeOfCommand typeOfCommand) {
        this.vehicleId = vehicleId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
