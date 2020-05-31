package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.CreateBrandCommand;
import saga.events.BrandCreatedEvent;
import saga.events.BrandCreatedFailedEvent;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.service.BrandService;

@Aggregate
public class BrandAggregate {
    @AggregateIdentifier
    private String brandAggregateId;

    @CommandHandler
    public BrandAggregate(CreateBrandCommand createBrandCommand, BrandService brandService) {
        try{
            brandService.add(createBrandCommand.getBrandDTO());
            AggregateLifecycle.apply(new BrandCreatedEvent(createBrandCommand.getBrandAggregateId()));
        } catch (ConversionFailedError conversionFailedError) {
            System.out.println(conversionFailedError.getMessage());
            conversionFailedError.printStackTrace();
            AggregateLifecycle.apply(new BrandCreatedFailedEvent(createBrandCommand.getBrandAggregateId(),
                    createBrandCommand.getBrandId(),
                    conversionFailedError.getMessage()));
        } catch (DuplicateEntity duplicateEntity) {
            System.out.println(duplicateEntity.getMessage());
            duplicateEntity.printStackTrace();
            AggregateLifecycle.apply(new BrandCreatedFailedEvent(createBrandCommand.getBrandAggregateId(),
                    createBrandCommand.getBrandId(),
                    duplicateEntity.getMessage()));

        }

    }

    @EventSourcingHandler
    protected void on(BrandCreatedEvent brandCreatedEvent) {
        this.brandAggregateId = brandCreatedEvent.getBrandAggregateId();
    }

    @EventSourcingHandler
    protected void on(BrandCreatedFailedEvent brandCreatedFailedEvent) {
        this.brandAggregateId = brandCreatedFailedEvent.getBrandAggregateId();
    }
}
