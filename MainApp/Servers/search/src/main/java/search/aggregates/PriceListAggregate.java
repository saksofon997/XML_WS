package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.priceListCommands.ReplicatePriceListCommand;
import saga.events.priceListEvents.PriceListReplicatedEvent;
import saga.events.priceListEvents.PriceListReplicatedFailedEvent;
import search.service.PricelistService;

@Aggregate
public class PriceListAggregate {
    @AggregateIdentifier
    private String priceListAggregateId;

    @CommandHandler
    public PriceListAggregate(ReplicatePriceListCommand replicatePriceListCommand, PricelistService priceListService) {
        System.out.println("USO SAM U SEARCH PRICELIST");
        try{
            if(replicatePriceListCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                priceListService.add(replicatePriceListCommand.getPriceListDTO());
                AggregateLifecycle.apply(new PriceListReplicatedEvent(replicatePriceListCommand.getPriceListAggregateId(), TypeOfCommand.CREATE));
            } else if (replicatePriceListCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                priceListService.update(replicatePriceListCommand.getPriceListId(),replicatePriceListCommand.getPriceListDTO());
                AggregateLifecycle.apply(new PriceListReplicatedEvent(replicatePriceListCommand.getPriceListAggregateId(), TypeOfCommand.UPDATE));
            } else {
                priceListService.delete(replicatePriceListCommand.getPriceListId());
                AggregateLifecycle.apply(new PriceListReplicatedEvent(replicatePriceListCommand.getPriceListAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new PriceListReplicatedFailedEvent(replicatePriceListCommand.getPriceListAggregateId(),
                    replicatePriceListCommand.getPriceListId(),
                    e.getMessage(), replicatePriceListCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(PriceListReplicatedEvent priceListReplicatedEvent) {
        this.priceListAggregateId = priceListReplicatedEvent.getPriceListAggregateId();
    }

    @EventSourcingHandler
    protected void on(PriceListReplicatedFailedEvent priceListReplicatedFailedEvent) {
        this.priceListAggregateId = priceListReplicatedFailedEvent.getPriceListAggregateId();
    }
}
