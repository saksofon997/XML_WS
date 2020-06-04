package rental.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rental.service.RentalService;
import saga.commands.manualOccupancyCommands.RejectRentalsCommand;
import saga.events.manualOccupancyEvents.RejectRentalsEvent;
import saga.events.manualOccupancyEvents.RejectRentalsFailedEvent;

@Aggregate
public class ManualOccupancyAggregate {
    @AggregateIdentifier
    private String occupancyAggregateId;

    @CommandHandler
    public ManualOccupancyAggregate(RejectRentalsCommand rejectRentalsCommand, RentalService rentalService) {
        System.out.println("USO SAM U RENTAL");
        try{
            rentalService.rejectRentalsFromTo(rejectRentalsCommand.getVehicleId(), rejectRentalsCommand.getOccupancyDTO());
            AggregateLifecycle.apply(new RejectRentalsEvent(rejectRentalsCommand.getOccupancyAggregateId()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new RejectRentalsFailedEvent(rejectRentalsCommand.getOccupancyAggregateId(),
                    rejectRentalsCommand.getOccupancyId(),
                    e.getMessage()));
        }

    }

    @EventSourcingHandler
    protected void on(RejectRentalsEvent rejectRentalsEvent) {
        this.occupancyAggregateId = rejectRentalsEvent.getOccupancyAggregateId();
    }
}
