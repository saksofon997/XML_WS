package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.manualOccupancyCommands.ManualOccupancyCommand;
import saga.events.manualOccupancyEvents.ManualOccupancyEvent;


@Aggregate
public class ManualOccupancyAggregate {

    @AggregateIdentifier
    private String occupancyId;

    public ManualOccupancyAggregate() {}

    @CommandHandler
    public ManualOccupancyAggregate(ManualOccupancyCommand manualOccupancyCommand) {
        System.out.println("Creating manual occupancy main event... ");
        AggregateLifecycle.apply(new ManualOccupancyEvent(manualOccupancyCommand.getOccupancyId(), manualOccupancyCommand.getOccupancyDTO(), manualOccupancyCommand.getVehicleId()));
    }

    @EventSourcingHandler
    public void on(ManualOccupancyEvent manualOccupancyEvent) {
        System.out.println("Setting manual occupancy aggregate ID...");
        System.out.println(manualOccupancyEvent.getOccupancyId());
        this.occupancyId = manualOccupancyEvent.getOccupancyId().toString() + "manualOccupancyAggregate";
    }
}
