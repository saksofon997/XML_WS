package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.ReplicateVehicleCommand;
import saga.commands.RollbackVehicleCommand;
import saga.events.VehicleCreatedEvent;
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
    public void handle(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("Saga invoked");

        String vehicleAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("vehicleAggregateId", vehicleAggregateId);

        commandGateway.send(new ReplicateVehicleCommand(vehicleAggregateId, vehicleCreatedEvent.getVehicleId(),
                vehicleCreatedEvent.getVehicleDTO()));
    }

    @SagaEventHandler(associationProperty = "vehicleAggregateId")
    public void handle(VehicleReplicatedEvent vehicleReplicatedEvent) {
        System.out.println("Saga finishing, both order and ticket created!");

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "vehicleAggregateId")
    public void handle(VehicleReplicatedFailedEvent vehicleReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollbackVehicleCommand(vehicleReplicatedFailedEvent.getVehicleId(), vehicleReplicatedFailedEvent.getReason()));
    }

    @SagaEventHandler(associationProperty = "vehicleId")
    public void handle(VehicleRollbackEvent vehicleUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }

}
