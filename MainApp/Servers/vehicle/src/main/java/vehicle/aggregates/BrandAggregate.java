package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.MainBrandCommand;
import saga.commands.RollBackBrandCommand;
import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;
import saga.events.BrandMainEvent;
import saga.events.BrandRollbackEvent;
import vehicle.service.BrandService;

import java.util.UUID;

@Aggregate
public class BrandAggregate {

    @AggregateIdentifier
    private String brandId;

    public BrandAggregate() {}

    @CommandHandler
    public BrandAggregate(MainBrandCommand mainBrandCommand) {
        System.out.println("Creating brand main event... ");
        AggregateLifecycle.apply(new BrandMainEvent(mainBrandCommand.getBrandId(),mainBrandCommand.getBrandDTO(), mainBrandCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(BrandMainEvent brandMainEvent) {
        System.out.println("Setting brand aggregate ID...");
        System.out.println(brandMainEvent.getBrandId());
        this.brandId = brandMainEvent.getBrandId().toString() + "brandAggregate" + brandMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackBrandCommand rollbackBrandCommand, BrandService brandService) {
        System.out.println("Performing rollback for brand...");
        try{
            if (rollbackBrandCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                brandService.deletePermanent(rollbackBrandCommand.getBrandId());
                System.out.println("Created vehicle event rollbacked -> vehicle deleted.");
            } else if (rollbackBrandCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new BrandRollbackEvent(rollbackBrandCommand.getBrandId(), rollbackBrandCommand.getTypeOfCommand()));
    }
}
