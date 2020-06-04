package saga.events.vehicleOccupancyEvents;

import saga.commands.TypeOfCommand;

public class OccupancyReplicatedEvent {
    private String occupancyAggregateId;
    private TypeOfCommand typeOfCommand;

    public OccupancyReplicatedEvent() {
    }

    public OccupancyReplicatedEvent(String occupancyAggregateId, TypeOfCommand typeOfCommand) {
        this.occupancyAggregateId = occupancyAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getOccupancyAggregateId() {
        return occupancyAggregateId;
    }

    public void setOccupancyAggregateId(String occupancyAggregateId) {
        this.occupancyAggregateId = occupancyAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
