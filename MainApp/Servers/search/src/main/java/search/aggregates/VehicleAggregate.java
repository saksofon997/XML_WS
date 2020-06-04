package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.ReplicateVehicleCommand;
import saga.commands.TypeOfCommand;
import saga.events.VehicleReplicatedEvent;
import saga.events.VehicleReplicatedFailedEvent;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.service.VehicleService;

@Aggregate
public class VehicleAggregate {
    @AggregateIdentifier
    private String vehicledAggregateId;

    @CommandHandler
    public VehicleAggregate(ReplicateVehicleCommand replicateVehicleCommand, VehicleService vehicleService) {
        System.out.println("USO SAM U SEARCH VEHICLE");
        try{
            if(replicateVehicleCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                vehicleService.add(replicateVehicleCommand.getVehicleDTO());
                AggregateLifecycle.apply(new VehicleReplicatedEvent(replicateVehicleCommand.getVehicleAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateVehicleCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                vehicleService.update(replicateVehicleCommand.getVehicleId(),replicateVehicleCommand.getVehicleDTO());
                AggregateLifecycle.apply(new VehicleReplicatedEvent(replicateVehicleCommand.getVehicleAggregateId(), TypeOfCommand.UPDATE));
            } else {
                vehicleService.delete(replicateVehicleCommand.getVehicleId());
                AggregateLifecycle.apply(new VehicleReplicatedEvent(replicateVehicleCommand.getVehicleAggregateId(), TypeOfCommand.DELETE));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
                AggregateLifecycle.apply(new VehicleReplicatedFailedEvent(replicateVehicleCommand.getVehicleAggregateId(),
                        replicateVehicleCommand.getVehicleId(),
                        e.getMessage(), replicateVehicleCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(VehicleReplicatedEvent vehicleReplicatedEvent) {
        this.vehicledAggregateId = vehicleReplicatedEvent.getVehicleAggregateId();
    }

    @EventSourcingHandler
    protected void on(VehicleReplicatedFailedEvent vehicleReplicatedFailedEvent) {
        this.vehicledAggregateId = vehicleReplicatedFailedEvent.getVehicleAggregateId();
    }
}
