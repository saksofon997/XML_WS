package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateTransmissionCommand;
import saga.commands.vehiclePartsCommands.RollBackTransmissionCommand;
import saga.events.vehiclePartsEvents.*;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class TransmissionSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "transmissionId")
    public void handle(TransmissionMainEvent transmissionMainEvent) {
        System.out.println("Saga invoked for transmission.");

        String transmissionAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("transmissionAggregateId", transmissionAggregateId);

        commandGateway.send(new ReplicateTransmissionCommand(transmissionAggregateId, transmissionMainEvent.getTransmissionId(),
                transmissionMainEvent.getTransmissionDTO(), transmissionMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "transmissionAggregateId")
    public void handle(TransmissionReplicatedEvent transmissionReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (transmissionReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Transmission created and replicated successfully!");
        } else if (transmissionReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Transmission updated and update replicated successfully!");
        } else {
            System.out.println(message + "Transmission deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "transmissionAggregateId")
    public void handle(TransmissionReplicatedFailedEvent transmissionReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackTransmissionCommand(transmissionReplicatedFailedEvent.getTransmissionId(),
                transmissionReplicatedFailedEvent.getReason(),
                transmissionReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "transmissionId")
    public void handle(TransmissionRollbackEvent transmissionUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
