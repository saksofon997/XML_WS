package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.ReplicateBrandCommand;
import saga.commands.ReplicateVehicleCommand;
import saga.commands.RollBackBrandCommand;
import saga.commands.RollbackVehicleCommand;
import saga.dto.BrandDTO;
import saga.events.*;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class VehicleManagementSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "brandId")
    public void handle(BrandCreatedEvent brandCreatedEvent) {
        System.out.println("Saga invoked");

        String brandAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("brandAggregateId", brandAggregateId);

        commandGateway.send(new ReplicateBrandCommand(brandAggregateId, brandCreatedEvent.getBrandId(),
                brandCreatedEvent.getBrandDTO()));
    }

    @SagaEventHandler(associationProperty = "brandAggregateId")
    public void handle(BrandReplicatedEvent brandReplicatedEvent) {
        System.out.println("Saga finishing, both order and ticket created!");

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "brandAggregateId")
    public void handle(BrandReplicatedFailedEvent brandReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackBrandCommand(brandReplicatedFailedEvent.getBrandId(), brandReplicatedFailedEvent.getReason()));
    }

    @SagaEventHandler(associationProperty = "brandId")
    public void handle(BrandRollbackEvent brandUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
