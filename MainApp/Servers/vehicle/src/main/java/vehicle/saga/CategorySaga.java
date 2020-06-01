package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateCategoryCommand;
import saga.commands.vehiclePartsCommands.RollBackCategoryCommand;
import saga.events.vehiclePartsEvents.CategoryMainEvent;
import saga.events.vehiclePartsEvents.CategoryReplicatedEvent;
import saga.events.vehiclePartsEvents.CategoryReplicatedFailedEvent;
import saga.events.vehiclePartsEvents.CategoryRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class CategorySaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "categoryId")
    public void handle(CategoryMainEvent categoryMainEvent) {
        System.out.println("Saga invoked for category.");

        String categoryAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("categoryAggregateId", categoryAggregateId);

        commandGateway.send(new ReplicateCategoryCommand(categoryAggregateId, categoryMainEvent.getCategoryId(),
                categoryMainEvent.getCategoryDTO(), categoryMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "categoryAggregateId")
    public void handle(CategoryReplicatedEvent categoryReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (categoryReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Category created and replicated successfully!");
        } else if (categoryReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Category updated and update replicated successfully!");
        } else {
            System.out.println(message + "Category deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "categoryAggregateId")
    public void handle(CategoryReplicatedFailedEvent categoryReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackCategoryCommand(categoryReplicatedFailedEvent.getCategoryId(),
                categoryReplicatedFailedEvent.getReason(),
                categoryReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "categoryId")
    public void handle(CategoryRollbackEvent categoryUpdatedEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
