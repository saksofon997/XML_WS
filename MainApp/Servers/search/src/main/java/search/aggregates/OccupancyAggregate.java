package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehicleOccupancyCommands.ReplicateOccupancyCommand;
import saga.events.reviewEvents.ReviewReplicatedEvent;
import saga.events.reviewEvents.ReviewReplicatedFailedEvent;
import saga.events.vehicleOccupancyEvents.OccupancyReplicatedEvent;
import saga.events.vehicleOccupancyEvents.OccupancyReplicatedFailedEvent;
import search.service.VehicleOccupancyService;

@Aggregate
public class OccupancyAggregate {
    @AggregateIdentifier
    private String occupancyAggregateId;

    @CommandHandler
    public OccupancyAggregate(ReplicateOccupancyCommand replicateOccupancyCommand, VehicleOccupancyService occupancyService) {
        System.out.println("USO SAM U SEARCH OCCUPANCY");
        try{
            if(replicateOccupancyCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                occupancyService.add(replicateOccupancyCommand.getVehicleId(),replicateOccupancyCommand.getOccupancyDTO() );
                AggregateLifecycle.apply(new OccupancyReplicatedEvent(replicateOccupancyCommand.getOccupancyAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateOccupancyCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                occupancyService.update(replicateOccupancyCommand.getVehicleId(), replicateOccupancyCommand.getVehicleOccupancyId(), replicateOccupancyCommand.getOccupancyDTO());
                AggregateLifecycle.apply(new OccupancyReplicatedEvent(replicateOccupancyCommand.getOccupancyAggregateId(), TypeOfCommand.UPDATE));
            } else {
                occupancyService.delete(replicateOccupancyCommand.getVehicleId(), replicateOccupancyCommand.getVehicleOccupancyId());
                AggregateLifecycle.apply(new OccupancyReplicatedEvent(replicateOccupancyCommand.getOccupancyAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new OccupancyReplicatedFailedEvent(replicateOccupancyCommand.getOccupancyAggregateId(),
                    replicateOccupancyCommand.getVehicleOccupancyId(),
                    replicateOccupancyCommand.getVehicleId(),
                    e.getMessage(), replicateOccupancyCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(OccupancyReplicatedEvent occupancyReplicatedEvent) {
        this.occupancyAggregateId = occupancyReplicatedEvent.getOccupancyAggregateId();
    }

    @EventSourcingHandler
    protected void on(OccupancyReplicatedFailedEvent occupancyReplicatedFailedEvent) {
        this.occupancyAggregateId = occupancyReplicatedFailedEvent.getOccupancyAggregateId();
    }
}
