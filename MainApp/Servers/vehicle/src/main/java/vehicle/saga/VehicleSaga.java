package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.ReplicateVehicleCommand;
import saga.commands.RollbackVehicleCommand;
import saga.commands.TypeOfCommand;
import saga.events.VehicleMainEvent;
import saga.events.VehicleReplicatedEvent;
import saga.events.VehicleReplicatedFailedEvent;
import saga.events.VehicleRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class VehicleSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "vehicleId")
    public void handle(VehicleMainEvent vehicleMainEvent) {
        System.out.println("Saga invoked");

        String vehicleAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("vehicleAggregateId", vehicleAggregateId);

        commandGateway.send(new ReplicateVehicleCommand(vehicleAggregateId, vehicleMainEvent.getVehicleId(),
                vehicleMainEvent.getVehicleDTO(), vehicleMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "vehicleAggregateId")
    public void handle(VehicleReplicatedEvent vehicleReplicatedEvent) {
        System.out.println("Saga finishing...");
        String message = "Saga finishing... \n";
        if (vehicleReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Vehicle created and replicated successfully!");
        } else if (vehicleReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Vehicle updated and update replicated successfully!");
        } else {
            System.out.println(message + "Vehicle deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "vehicleAggregateId")
    public void handle(VehicleReplicatedFailedEvent vehicleReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollbackVehicleCommand(vehicleReplicatedFailedEvent.getVehicleId(), vehicleReplicatedFailedEvent.getReason(), vehicleReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "vehicleId")
    public void handle(VehicleRollbackEvent vehicleRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }

}
