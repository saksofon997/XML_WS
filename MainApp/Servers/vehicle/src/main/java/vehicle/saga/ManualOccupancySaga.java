package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.manualOccupancyCommands.RejectRentalsCommand;
import saga.events.manualOccupancyEvents.ManualOccupancyEvent;
import saga.events.manualOccupancyEvents.RejectRentalsEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class ManualOccupancySaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "occupancyId")
    public void handle(ManualOccupancyEvent manualOccupancyEvent) {
        System.out.println("Saga invoked for manual occupancy.");

        String occupancyAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("occupancyAggregateId", occupancyAggregateId);

        commandGateway.send(new RejectRentalsCommand(occupancyAggregateId, manualOccupancyEvent.getOccupancyId(),
                manualOccupancyEvent.getOccupancyDTO(), manualOccupancyEvent.getVehicleId()));
    }

    @SagaEventHandler(associationProperty = "occupancyAggregateId")
    public void handle(RejectRentalsEvent rejectRentalsEvent) {
        System.out.println("Saga finishing... \nRentals rejected successfully!");
        SagaLifecycle.end();
    }
}
