package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateFuelCommand;
import saga.commands.vehiclePartsCommands.RollBackFuelCommand;
import saga.events.vehiclePartsEvents.*;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class FuelSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "fuelId")
    public void handle(FuelMainEvent fuelMainEvent) {
        System.out.println("Saga invoked for fuel.");

        String fuelAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("fuelAggregateId", fuelAggregateId);

        commandGateway.send(new ReplicateFuelCommand(fuelAggregateId, fuelMainEvent.getFuelId(),
                fuelMainEvent.getFuelDTO(), fuelMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "fuelAggregateId")
    public void handle(FuelReplicatedEvent fuelReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (fuelReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Fuel created and replicated successfully!");
        } else if (fuelReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Fuel updated and update replicated successfully!");
        } else {
            System.out.println(message + "Fuel deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "fuelAggregateId")
    public void handle(FuelReplicatedFailedEvent fuelReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackFuelCommand(fuelReplicatedFailedEvent.getFuelId(),
                fuelReplicatedFailedEvent.getReason(),
                fuelReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "fuelId")
    public void handle(FuelRollbackEvent fuelUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
