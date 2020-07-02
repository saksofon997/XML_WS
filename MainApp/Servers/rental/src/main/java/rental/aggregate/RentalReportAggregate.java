package rental.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rental.service.RentalReportService;
import saga.commands.rentalReportCommands.NewRentalReportCommand;
import saga.commands.rentalReportCommands.RollBackNewRentalReportCommand;
import saga.events.rentalReportEvents.NewRentalReportEvent;
import saga.events.rentalReportEvents.NewRentalReportRollbackEvent;

import java.util.UUID;

@Aggregate
public class RentalReportAggregate {
    @AggregateIdentifier
    private String newRentalReportAggregateId;

    @CommandHandler
    public RentalReportAggregate(NewRentalReportCommand newRentalReportCommand) {
        System.out.println("Creating new rental report event... ");
        AggregateLifecycle.apply(new NewRentalReportEvent(newRentalReportCommand.getRentalReportId(),
                newRentalReportCommand.getVehicleId(), newRentalReportCommand.getMileage()));
    }

    @EventSourcingHandler
    public void on(NewRentalReportEvent newRentalReportEvent) {
        System.out.println("Setting new rental report aggregate ID...");
        this.newRentalReportAggregateId = newRentalReportEvent.getRentalReportId().toString() + "newRentalReportAggregate" + newRentalReportEvent.getVehicleId() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackNewRentalReportCommand rollBackNewRentalReportCommand, RentalReportService rentalReportService) {
        System.out.println("Performing rollback for rental report...");
        try{
            rentalReportService.delete(rollBackNewRentalReportCommand.getRentalReportId());
            System.out.println("Mileage update failed -> report deleted.");
        } catch (Exception e) {
            System.out.println("Failed rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new NewRentalReportRollbackEvent(rollBackNewRentalReportCommand.getRentalReportId()));
    }
}
