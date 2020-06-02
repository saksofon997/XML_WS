package saga.events.vehicleOccupancyEvents;

import saga.commands.TypeOfCommand;

public class OccupancyMainFailedEvent {
    private String occupancyAggregateId;
    private Long vehicleOccupancyId;
    private Long vehicleId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public OccupancyMainFailedEvent() {
    }

    public OccupancyMainFailedEvent(String occupancyAggregateId, Long vehicleOccupancyId, Long vehicleId, String reason, TypeOfCommand typeOfCommand) {
        this.occupancyAggregateId = occupancyAggregateId;
        this.vehicleOccupancyId = vehicleOccupancyId;
        this.vehicleId = vehicleId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getOccupancyAggregateId() {
        return occupancyAggregateId;
    }

    public void setOccupancyAggregateId(String occupancyAggregateId) {
        this.occupancyAggregateId = occupancyAggregateId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
