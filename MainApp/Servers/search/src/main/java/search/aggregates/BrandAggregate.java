package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.ReplicateBrandCommand;
import saga.events.BrandReplicatedEvent;
import saga.events.BrandReplicatedFailedEvent;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.service.BrandService;

@Aggregate
public class BrandAggregate {
    @AggregateIdentifier
    private String brandAggregateId;

    @CommandHandler
    public BrandAggregate(ReplicateBrandCommand replicateBrandCommand, BrandService brandService) {
        System.out.println("USO SAM U SEARCH");
        try{
            brandService.add(replicateBrandCommand.getBrandDTO());
            AggregateLifecycle.apply(new BrandReplicatedEvent(replicateBrandCommand.getBrandAggregateId()));
        } catch (ConversionFailedError conversionFailedError) {
            System.out.println(conversionFailedError.getMessage());
            conversionFailedError.printStackTrace();
            AggregateLifecycle.apply(new BrandReplicatedFailedEvent(replicateBrandCommand.getBrandAggregateId(),
                    replicateBrandCommand.getBrandId(),
                    conversionFailedError.getMessage()));
        } catch (DuplicateEntity duplicateEntity) {
            System.out.println(duplicateEntity.getMessage());
            duplicateEntity.printStackTrace();
            AggregateLifecycle.apply(new BrandReplicatedFailedEvent(replicateBrandCommand.getBrandAggregateId(),
                    replicateBrandCommand.getBrandId(),
                    duplicateEntity.getMessage()));

        }

    }

    @EventSourcingHandler
    protected void on(BrandReplicatedEvent brandReplicatedEvent) {
        this.brandAggregateId = brandReplicatedEvent.getBrandAggregateId();
    }

    @EventSourcingHandler
    protected void on(BrandReplicatedFailedEvent brandReplicatedFailedEvent) {
        this.brandAggregateId = brandReplicatedFailedEvent.getBrandAggregateId();
    }
}
