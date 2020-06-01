package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainCategoryCommand;
import saga.commands.vehiclePartsCommands.RollBackCategoryCommand;
import saga.events.vehiclePartsEvents.CategoryMainEvent;
import saga.events.vehiclePartsEvents.CategoryRollbackEvent;
import vehicle.service.CategoryService;

import java.util.UUID;

@Aggregate
public class CategoryAggregate {

    @AggregateIdentifier
    private String categoryId;

    public CategoryAggregate() {}

    @CommandHandler
    public CategoryAggregate(MainCategoryCommand mainCategoryCommand) {
        System.out.println("Creating category main event... ");
        AggregateLifecycle.apply(new CategoryMainEvent(mainCategoryCommand.getCategoryId(),mainCategoryCommand.getCategoryDTO(), mainCategoryCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(CategoryMainEvent categoryMainEvent) {
        System.out.println("Setting category aggregate ID...");
        System.out.println(categoryMainEvent.getCategoryId());
        this.categoryId = categoryMainEvent.getCategoryId().toString() + "categoryAggregate" + categoryMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackCategoryCommand rollbackCategoryCommand, CategoryService categoryService) {
        System.out.println("Performing rollback for category...");
        try{
            if (rollbackCategoryCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                categoryService.delete(rollbackCategoryCommand.getCategoryId());
                System.out.println("Created category event rollbacked -> category deleted.");
            } else if (rollbackCategoryCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new CategoryRollbackEvent(rollbackCategoryCommand.getCategoryId(), rollbackCategoryCommand.getTypeOfCommand()));
    }
}
