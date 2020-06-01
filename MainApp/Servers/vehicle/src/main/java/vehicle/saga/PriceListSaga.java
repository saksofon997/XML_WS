package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.priceListCommands.ReplicatePriceListCommand;
import saga.commands.priceListCommands.RollBackPriceListCommand;
import saga.events.priceListEvents.PriceListMainEvent;
import saga.events.priceListEvents.PriceListReplicatedEvent;
import saga.events.priceListEvents.PriceListReplicatedFailedEvent;
import saga.events.priceListEvents.PriceListRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class PriceListSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "priceListId")
    public void handle(PriceListMainEvent priceListMainEvent) {
        System.out.println("Saga invoked for price list.");

        String priceListAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("priceListAggregateId", priceListAggregateId);

        commandGateway.send(new ReplicatePriceListCommand(priceListAggregateId, priceListMainEvent.getPriceListId(),
                priceListMainEvent.getPriceListDTO(), priceListMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "priceListAggregateId")
    public void handle(PriceListReplicatedEvent priceListReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (priceListReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Price list created and replicated successfully!");
        } else if (priceListReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Price list updated and update replicated successfully!");
        } else {
            System.out.println(message + "Price list deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "priceListAggregateId")
    public void handle(PriceListReplicatedFailedEvent priceListReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackPriceListCommand(priceListReplicatedFailedEvent.getPriceListId(),
                priceListReplicatedFailedEvent.getReason(),
                priceListReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "priceListId")
    public void handle(PriceListRollbackEvent priceListRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
