package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateCategoryCommand;
import saga.events.vehiclePartsEvents.CategoryReplicatedEvent;
import saga.events.vehiclePartsEvents.CategoryReplicatedFailedEvent;
import search.service.CategoryService;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String categoryAggregateId;

    @CommandHandler
    public CategoryAggregate(ReplicateCategoryCommand replicateCategoryCommand, CategoryService categoryService) {
        System.out.println("USO SAM U SEARCH CATEGORY");
        try{
            if(replicateCategoryCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                categoryService.add(replicateCategoryCommand.getCategoryDTO());
                AggregateLifecycle.apply(new CategoryReplicatedEvent(replicateCategoryCommand.getCategoryAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateCategoryCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                categoryService.update(replicateCategoryCommand.getCategoryId(),replicateCategoryCommand.getCategoryDTO());
                AggregateLifecycle.apply(new CategoryReplicatedEvent(replicateCategoryCommand.getCategoryAggregateId(), TypeOfCommand.UPDATE));
            } else {
                categoryService.delete(replicateCategoryCommand.getCategoryId());
                AggregateLifecycle.apply(new CategoryReplicatedEvent(replicateCategoryCommand.getCategoryAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new CategoryReplicatedFailedEvent(replicateCategoryCommand.getCategoryAggregateId(),
                    replicateCategoryCommand.getCategoryId(),
                    e.getMessage(), replicateCategoryCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(CategoryReplicatedEvent categoryReplicatedEvent) {
        this.categoryAggregateId = categoryReplicatedEvent.getCategoryAggregateId();
    }

    @EventSourcingHandler
    protected void on(CategoryReplicatedFailedEvent categoryReplicatedFailedEvent) {
        this.categoryAggregateId = categoryReplicatedFailedEvent.getCategoryAggregateId();
    }
}
