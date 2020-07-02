package rental.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.rentalReportCommands.RentalReportMileageUpdateCommand;
import saga.commands.rentalReportCommands.RollBackNewRentalReportCommand;
import saga.events.rentalReportEvents.NewRentalReportEvent;
import saga.events.rentalReportEvents.NewRentalReportRollbackEvent;
import saga.events.rentalReportEvents.RentalReportMileageUpdateEvent;
import saga.events.rentalReportEvents.RentalReportMileageUpdateFailedEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class RentalReportSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "rentalId")
    public void handle(NewRentalReportEvent newRentalReportEvent) {
        System.out.println("Saga invoked for rental reserved.");

        String newRentalReportAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("newRentalReportAggregateId", newRentalReportAggregateId);

        commandGateway.send(new RentalReportMileageUpdateCommand(newRentalReportAggregateId, newRentalReportEvent.getRentalReportId(),
                newRentalReportEvent.getVehicleId(), newRentalReportEvent.getMileage()));
    }

    @SagaEventHandler(associationProperty = "newRentalReportAggregateId")
    public void handle(RentalReportMileageUpdateEvent rentalReportMileageUpdateEvent) {
        String message = "Saga finishing... \n";
        System.out.println(message + "Vehicle mileage updated successfully!");
        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "rentalReservedAggregateId")
    public void handle(RentalReportMileageUpdateFailedEvent rentalReportMileageUpdateFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackNewRentalReportCommand(rentalReportMileageUpdateFailedEvent.getRentalReportId(),
                rentalReportMileageUpdateFailedEvent.getReason()));
    }

    @SagaEventHandler(associationProperty = "rentalId")
    public void handle(NewRentalReportRollbackEvent newRentalReportRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
