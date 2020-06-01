package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateFuelCommand;
import saga.events.vehiclePartsEvents.FuelReplicatedEvent;
import saga.events.vehiclePartsEvents.FuelReplicatedFailedEvent;
import search.service.FuelService;

@Aggregate
public class FuelAggregate {
    @AggregateIdentifier
    private String fuelAggregateId;

    @CommandHandler
    public FuelAggregate(ReplicateFuelCommand replicateFuelCommand, FuelService fuelService) {
        System.out.println("USO SAM U SEARCH FUEL");
        try{
            if(replicateFuelCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                fuelService.add(replicateFuelCommand.getFuelDTO());
                AggregateLifecycle.apply(new FuelReplicatedEvent(replicateFuelCommand.getFuelAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateFuelCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                fuelService.update(replicateFuelCommand.getFuelId(),replicateFuelCommand.getFuelDTO());
                AggregateLifecycle.apply(new FuelReplicatedEvent(replicateFuelCommand.getFuelAggregateId(), TypeOfCommand.UPDATE));
            } else {
                fuelService.delete(replicateFuelCommand.getFuelId());
                AggregateLifecycle.apply(new FuelReplicatedEvent(replicateFuelCommand.getFuelAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new FuelReplicatedFailedEvent(replicateFuelCommand.getFuelAggregateId(),
                    replicateFuelCommand.getFuelId(),
                    e.getMessage(), replicateFuelCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(FuelReplicatedEvent fuelReplicatedEvent) {
        this.fuelAggregateId = fuelReplicatedEvent.getFuelAggregateId();
    }

    @EventSourcingHandler
    protected void on(FuelReplicatedFailedEvent fuelReplicatedFailedEvent) {
        this.fuelAggregateId = fuelReplicatedFailedEvent.getFuelAggregateId();
    }
}
