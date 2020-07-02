package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.rentalReservedCommands.RentalVehicleOccupancyAddCommand;
import saga.events.rentalReservedEvents.RentalVehicleOccupancyAddEvent;
import saga.events.rentalReservedEvents.RentalVehicleOccupancyAddFailedEvent;
import vehicle.service.VehicleOccupancyService;

@Aggregate
public class RentalReservedAggregate {
    @AggregateIdentifier
    private String rentalReservedAggregateId;

    @CommandHandler
    public RentalReservedAggregate(RentalVehicleOccupancyAddCommand rentalVehicleOccupancyAddCommand, VehicleOccupancyService vehicleOccupancyService) {
        System.out.println("Rental reserved -> vehicle occupancy update");
        try{
            vehicleOccupancyService.add(rentalVehicleOccupancyAddCommand.getVehicleId(), rentalVehicleOccupancyAddCommand.getOccupancyDTO());
            AggregateLifecycle.apply(new RentalVehicleOccupancyAddEvent(rentalVehicleOccupancyAddCommand.getRentalReservedAggregateId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new RentalVehicleOccupancyAddFailedEvent(rentalVehicleOccupancyAddCommand.getRentalReservedAggregateId(),
                    rentalVehicleOccupancyAddCommand.getRentalId(),
                    e.getMessage()));
        }

    }

    @EventSourcingHandler
    protected void on(RentalVehicleOccupancyAddEvent rentalVehicleOccupancyAddEvent) {
        this.rentalReservedAggregateId = rentalVehicleOccupancyAddEvent.getRentalReservedAggregateId();
    }
}
