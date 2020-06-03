package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import saga.commands.TypeOfCommand;
import saga.commands.modelCommands.ReplicateModelCommand;
import saga.events.modelEvents.ModelReplicatedEvent;
import saga.events.modelEvents.ModelReplicatedFailedEvent;
import search.service.ModelService;

public class ModelAggregate {
    @AggregateIdentifier
    private String modelAggregateId;

    @CommandHandler
    public ModelAggregate(ReplicateModelCommand replicateModelCommand, ModelService modelService) {
        System.out.println("USO SAM U SEARCH");
        try{
            if(replicateModelCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                modelService.add(replicateModelCommand.getBrandId(), replicateModelCommand.getModelDTO());
                AggregateLifecycle.apply(new ModelReplicatedEvent(replicateModelCommand.getModelAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateModelCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                modelService.update(replicateModelCommand.getBrandId(), replicateModelCommand.getModelId(),replicateModelCommand.getModelDTO());
                AggregateLifecycle.apply(new ModelReplicatedEvent(replicateModelCommand.getModelAggregateId(), TypeOfCommand.UPDATE));
            } else {
                modelService.delete(replicateModelCommand.getBrandId(), replicateModelCommand.getModelId());
                AggregateLifecycle.apply(new ModelReplicatedEvent(replicateModelCommand.getModelAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new ModelReplicatedFailedEvent(replicateModelCommand.getModelAggregateId(),
                                                                    replicateModelCommand.getBrandId(),
                                                                    replicateModelCommand.getModelId(),
                                                                    e.getMessage(),
                                                                    replicateModelCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(ModelReplicatedEvent modelReplicatedEvent) {
        this.modelAggregateId = modelReplicatedEvent.getModelAggregateId();
    }

    @EventSourcingHandler
    protected void on(ModelReplicatedFailedEvent modelReplicatedFailedEvent) {
        this.modelAggregateId = modelReplicatedFailedEvent.getModelAggregateId();
    }
}
