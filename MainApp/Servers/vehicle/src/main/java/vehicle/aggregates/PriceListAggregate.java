package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.priceListCommands.MainPriceListCommand;
import saga.commands.priceListCommands.RollBackPriceListCommand;
import saga.events.priceListEvents.PriceListMainEvent;
import saga.events.priceListEvents.PriceListRollbackEvent;
import vehicle.service.PricelistService;

import java.util.UUID;

@Aggregate
public class PriceListAggregate {

    @AggregateIdentifier
    private String priceListId;

    public PriceListAggregate() {}

    @CommandHandler
    public PriceListAggregate(MainPriceListCommand mainPriceListCommand) {
        System.out.println("Creating price list main event... ");
        AggregateLifecycle.apply(new PriceListMainEvent(mainPriceListCommand.getPriceListId(),mainPriceListCommand.getPriceListDTO(), mainPriceListCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(PriceListMainEvent priceListMainEvent) {
        System.out.println("Setting price list aggregate ID...");
        System.out.println(priceListMainEvent.getPriceListDTO());
        this.priceListId = priceListMainEvent.getPriceListId().toString() + "priceListAggregate" + priceListMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackPriceListCommand rollbackPriceListCommand, PricelistService pricelistService) {
        System.out.println("Performing rollback for price list...");
        try{
            if (rollbackPriceListCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                pricelistService.delete(rollbackPriceListCommand.getPriceListId());
                System.out.println("Created price list event rollbacked -> price list deleted.");
            } else if (rollbackPriceListCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new PriceListRollbackEvent(rollbackPriceListCommand.getPriceListId(), rollbackPriceListCommand.getTypeOfCommand()));
    }
}
