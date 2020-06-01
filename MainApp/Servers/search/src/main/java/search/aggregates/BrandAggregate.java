package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.ReplicateBrandCommand;
import saga.commands.TypeOfCommand;
import saga.events.BrandReplicatedEvent;
import saga.events.BrandReplicatedFailedEvent;
import saga.events.VehicleReplicatedEvent;
import saga.events.VehicleReplicatedFailedEvent;
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
            if(replicateBrandCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                brandService.add(replicateBrandCommand.getBrandDTO());
                AggregateLifecycle.apply(new BrandReplicatedEvent(replicateBrandCommand.getBrandAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateBrandCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                brandService.update(replicateBrandCommand.getBrandId(),replicateBrandCommand.getBrandDTO());
                AggregateLifecycle.apply(new BrandReplicatedEvent(replicateBrandCommand.getBrandAggregateId(), TypeOfCommand.UPDATE));
            } else {
                brandService.delete(replicateBrandCommand.getBrandId());
                AggregateLifecycle.apply(new BrandReplicatedEvent(replicateBrandCommand.getBrandAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new BrandReplicatedFailedEvent(replicateBrandCommand.getBrandAggregateId(),
                    replicateBrandCommand.getBrandId(),
                    e.getMessage(), replicateBrandCommand.getTypeOfCommand()));
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
