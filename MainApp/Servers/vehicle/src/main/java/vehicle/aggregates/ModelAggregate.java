package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.modelCommands.MainModelCommand;
import saga.commands.modelCommands.RollbackModelCommand;
import saga.events.modelEvents.ModelMainEvent;
import saga.events.modelEvents.ModelRollbackEvent;
import vehicle.service.ModelService;

import java.util.UUID;

@Aggregate
public class ModelAggregate {
    @AggregateIdentifier
    private String modelId;

    public ModelAggregate() {}

    @CommandHandler
    public ModelAggregate(MainModelCommand mainModelCommand) {
        System.out.println("Creating model main event... ");
        AggregateLifecycle.apply(new ModelMainEvent(mainModelCommand.getBrandId(), mainModelCommand.getModelId(), mainModelCommand.getModelDTO(), mainModelCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(ModelMainEvent modelMainEvent) {
        System.out.println("Setting model aggregate ID...");
        System.out.println(modelMainEvent.getModelId());
        this.modelId = modelMainEvent.getBrandId() + modelMainEvent.getModelId().toString() + "modelAggregate" + modelMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollbackModelCommand rollbackModelCommand, ModelService modelService) {
        System.out.println("Performing rollback for model...");
        try{
            if (rollbackModelCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                modelService.deletePermanent(rollbackModelCommand.getModelId());
                System.out.println("Created vehicle event rollbacked -> vehicle deleted.");
            } else if (rollbackModelCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new ModelRollbackEvent(rollbackModelCommand.getBrandId(), rollbackModelCommand.getModelId(), rollbackModelCommand.getTypeOfCommand()));
    }
}
