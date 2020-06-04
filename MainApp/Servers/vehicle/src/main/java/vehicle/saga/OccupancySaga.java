package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.reviewCommands.RollBackReviewCommand;
import saga.commands.vehicleOccupancyCommands.ReplicateOccupancyCommand;
import saga.events.vehicleOccupancyEvents.OccupancyMainEvent;
import saga.events.vehicleOccupancyEvents.OccupancyReplicatedEvent;
import saga.events.vehicleOccupancyEvents.OccupancyReplicatedFailedEvent;
import saga.events.vehicleOccupancyEvents.OccupancyRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class OccupancySaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "vehicleOccupancyId")
    public void handle(OccupancyMainEvent occupancyMainEvent) {
        System.out.println("Saga invoked for vehicle occupancy.");

        String occupancyAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("occupancyAggregateId", occupancyAggregateId);

        commandGateway.send(new ReplicateOccupancyCommand(occupancyAggregateId, occupancyMainEvent.getVehicleOccupancyId(),
                occupancyMainEvent.getVehicleId(),
                occupancyMainEvent.getOccupancyDTO(), occupancyMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "occupancyAggregateId")
    public void handle(OccupancyReplicatedEvent occupancyReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (occupancyReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Occupancy created and replicated successfully!");
        } else if (occupancyReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Occupancy updated and update replicated successfully!");
        } else {
            System.out.println(message + "Review deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "occupancyAggregateId")
    public void handle(OccupancyReplicatedFailedEvent occupancyReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackReviewCommand(occupancyReplicatedFailedEvent.getVehicleOccupancyId(),
                occupancyReplicatedFailedEvent.getVehicleId(),
                occupancyReplicatedFailedEvent.getReason(),
                occupancyReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "vehicleOccupancyId")
    public void handle(OccupancyRollbackEvent occupancyRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
