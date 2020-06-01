package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.ReplicateBrandCommand;
import saga.commands.RollBackBrandCommand;
import saga.commands.TypeOfCommand;
import saga.events.*;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class BrandSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "brandId")
    public void handle(BrandMainEvent brandMainEvent) {
        System.out.println("Saga invoked for brand.");

        String brandAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("brandAggregateId", brandAggregateId);

        commandGateway.send(new ReplicateBrandCommand(brandAggregateId, brandMainEvent.getBrandId(),
                brandMainEvent.getBrandDTO(), brandMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "brandAggregateId")
    public void handle(BrandReplicatedEvent brandReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (brandReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Brand created and replicated successfully!");
        } else if (brandReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Brand updated and update replicated successfully!");
        } else {
            System.out.println(message + "Brand deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "brandAggregateId")
    public void handle(BrandReplicatedFailedEvent brandReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackBrandCommand(brandReplicatedFailedEvent.getBrandId(),
                                                     brandReplicatedFailedEvent.getReason(),
                                                     brandReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "brandId")
    public void handle(BrandRollbackEvent brandUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
