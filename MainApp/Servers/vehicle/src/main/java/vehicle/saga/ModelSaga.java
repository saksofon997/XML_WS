package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.ReplicateBrandCommand;
import saga.commands.RollBackBrandCommand;
import saga.commands.TypeOfCommand;
import saga.commands.modelCommands.ReplicateModelCommand;
import saga.commands.modelCommands.RollbackModelCommand;
import saga.events.BrandMainEvent;
import saga.events.BrandReplicatedEvent;
import saga.events.BrandReplicatedFailedEvent;
import saga.events.BrandRollbackEvent;
import saga.events.modelEvents.ModelMainEvent;
import saga.events.modelEvents.ModelReplicatedEvent;
import saga.events.modelEvents.ModelReplicatedFailedEvent;
import saga.events.modelEvents.ModelRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class ModelSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "modelId")
    public void handle(ModelMainEvent modelMainEvent) {
        System.out.println("Saga invoked for model.");

        String modelAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("modelAggregateId", modelAggregateId);

        commandGateway.send(new ReplicateModelCommand(modelAggregateId, modelMainEvent.getBrandId(),
                modelMainEvent.getModelId(), modelMainEvent.getModelDTO(), modelMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "modelAggregateId")
    public void handle(ModelReplicatedEvent modelReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (modelReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Model created and replicated successfully!");
        } else if (modelReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Model updated and update replicated successfully!");
        } else {
            System.out.println(message + "Model deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "modelAggregateId")
    public void handle(ModelReplicatedFailedEvent modelReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollbackModelCommand(modelReplicatedFailedEvent.getBrandId(),
                modelReplicatedFailedEvent.getModelId(),
                modelReplicatedFailedEvent.getReason(),
                modelReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "modelId")
    public void handle(ModelRollbackEvent modelRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
