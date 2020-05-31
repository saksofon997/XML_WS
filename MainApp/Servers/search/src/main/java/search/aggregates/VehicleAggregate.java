package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.ReplicateVehicleCommand;
import saga.events.VehicleReplicatedEvent;
import saga.events.VehicleReplicatedFailedEvent;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.service.VehicleService;

@Aggregate
public class VehicleAggregate {
    @AggregateIdentifier
    private String vehicledAggregateId;

    @CommandHandler
    public VehicleAggregate(ReplicateVehicleCommand replicateVehicleCommand, VehicleService vehicleService) {
        System.out.println("USO SAM U SEARCH DODAVANJE VEHICLE-A");
        try{
            vehicleService.add(replicateVehicleCommand.getVehicleDTO());
            AggregateLifecycle.apply(new VehicleReplicatedEvent(replicateVehicleCommand.getVehicleAggregateId()));
        } catch (ConversionFailedError conversionFailedError) {
            System.out.println(conversionFailedError.getMessage());
            conversionFailedError.printStackTrace();
            AggregateLifecycle.apply(new VehicleReplicatedFailedEvent(replicateVehicleCommand.getVehicleAggregateId(),
                    replicateVehicleCommand.getVehicleId(),
                    conversionFailedError.getMessage()));
        } catch (DuplicateEntity duplicateEntity) {
            System.out.println(duplicateEntity.getMessage());
            duplicateEntity.printStackTrace();
            AggregateLifecycle.apply(new VehicleReplicatedFailedEvent(replicateVehicleCommand.getVehicleAggregateId(),
                    replicateVehicleCommand.getVehicleId(),
                    duplicateEntity.getMessage()));

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
