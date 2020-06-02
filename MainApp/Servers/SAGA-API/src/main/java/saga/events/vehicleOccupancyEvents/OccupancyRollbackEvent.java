package saga.events.vehicleOccupancyEvents;

import saga.commands.TypeOfCommand;

public class OccupancyRollbackEvent {
    private Long vehicleOccupancyId;
    private Long vehicleId;
    private TypeOfCommand typeOfCommand;

    public OccupancyRollbackEvent() {
    }

    public OccupancyRollbackEvent(Long vehicleOccupancyId, Long vehicleId, TypeOfCommand typeOfCommand) {
        this.vehicleOccupancyId = vehicleOccupancyId;
        this.vehicleId = vehicleId;
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

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
