package rental.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rental.service.RentalReportService;
import rental.service.RentalService;
import saga.commands.rentalReportCommands.NewRentalReportCommand;
import saga.commands.rentalReportCommands.RollBackNewRentalReportCommand;
import saga.commands.rentalReservedCommands.RentalReservedCommand;
import saga.commands.rentalReservedCommands.RollBackRentalReservedCommand;
import saga.events.rentalReportEvents.NewRentalReportEvent;
import saga.events.rentalReportEvents.NewRentalReportRollbackEvent;
import saga.events.rentalReservedEvents.RentalReservedEvent;
import saga.events.rentalReservedEvents.RentalReservedRollbackEvent;

import java.util.UUID;

@Aggregate
public class RentalReservedAggregate {
    @AggregateIdentifier
    private String rentalReservedAggregateId;

    @CommandHandler
    public RentalReservedAggregate(RentalReservedCommand rentalReservedCommand) {
        System.out.println("Creating rental reserved event... ");
        AggregateLifecycle.apply(new RentalReservedEvent(rentalReservedCommand.getRentalId(),
                rentalReservedCommand.getOccupancyDTO(), rentalReservedCommand.getVehicleId()));
    }

    @EventSourcingHandler
    public void on(RentalReservedEvent rentalReservedEvent) {
        System.out.println("Setting new rental report aggregate ID...");
        this.rentalReservedAggregateId = rentalReservedEvent.getRentalId().toString() + "rentalReservedAggregate" + rentalReservedEvent.getRentalId() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackRentalReservedCommand rollBackRentalReservedCommand, RentalService rentalService) {
        System.out.println("Performing rollback for rental reserved...");
        try{
            rentalService.sagaRollback(rollBackRentalReservedCommand.getRentalId());
            System.out.println("Vehicle occupancy update failed -> rental set to pending.");
        } catch (Exception e) {
            System.out.println("Failed rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new RentalReservedRollbackEvent(rollBackRentalReservedCommand.getRentalId()));
    }

}
