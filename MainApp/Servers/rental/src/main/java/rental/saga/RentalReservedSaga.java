package rental.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.rentalReservedCommands.RentalVehicleOccupancyAddCommand;
import saga.commands.rentalReservedCommands.RollBackRentalReservedCommand;
import saga.events.rentalReservedEvents.RentalReservedEvent;
import saga.events.rentalReservedEvents.RentalReservedRollbackEvent;
import saga.events.rentalReservedEvents.RentalVehicleOccupancyAddEvent;
import saga.events.rentalReservedEvents.RentalVehicleOccupancyAddFailedEvent;


import javax.inject.Inject;
import java.util.UUID;

@Saga
public class RentalReservedSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "rentalId")
    public void handle(RentalReservedEvent rentalReservedEvent) {
        System.out.println("Saga invoked for rental reserved.");

        String rentalReservedAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("rentalReservedAggregateId", rentalReservedAggregateId);

        commandGateway.send(new RentalVehicleOccupancyAddCommand(rentalReservedAggregateId,
                rentalReservedEvent.getRentalId(), rentalReservedEvent.getOccupancyDTO(),
                rentalReservedEvent.getVehicleId()));
    }

    @SagaEventHandler(associationProperty = "rentalReservedAggregateId")
    public void handle(RentalVehicleOccupancyAddEvent rentalVehicleOccupancyAddEvent) {
        String message = "Saga finishing... \n";
        System.out.println(message + "Rental reserved and vehicle occupancy updated successfully!");
        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "rentalReservedAggregateId")
    public void handle(RentalVehicleOccupancyAddFailedEvent rentalVehicleOccupancyAddFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackRentalReservedCommand(rentalVehicleOccupancyAddFailedEvent.getRentalId(),
                rentalVehicleOccupancyAddFailedEvent.getReason()));
    }

    @SagaEventHandler(associationProperty = "rentalId")
    public void handle(RentalReservedRollbackEvent rentalReservedRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
