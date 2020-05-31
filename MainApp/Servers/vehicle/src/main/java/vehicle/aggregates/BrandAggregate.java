package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.CreateBrandCommand;
import saga.commands.RollBackBrandCommand;
import saga.events.BrandCreatedEvent;
import saga.events.BrandRollbackEvent;
import vehicle.service.BrandService;

@Aggregate
public class BrandAggregate {

    @AggregateIdentifier
    private Long brandId;

    public BrandAggregate() {}

    @CommandHandler
    public BrandAggregate(CreateBrandCommand createBrandCommand) {
        System.out.println("ODJE SAM ");
        AggregateLifecycle.apply(new BrandCreatedEvent(createBrandCommand.getBrandId(),createBrandCommand.getBrandDTO()));
    }

    @EventSourcingHandler
    public void on(BrandCreatedEvent brandCreatedEvent) {
        System.out.println("ODJE SAM 1");
        this.brandId = brandCreatedEvent.getBrandId();
    }

    @CommandHandler
    public void on(RollBackBrandCommand rollbackOrderCommand, BrandService brandService) {
        //brandService.update(rollbackOrderCommand.getBrandId(), OrderStatus.REJECTED);
        System.out.println("ROLBACKOVANJE");
        AggregateLifecycle.apply(new BrandRollbackEvent(rollbackOrderCommand.getBrandId()));
    }
}
