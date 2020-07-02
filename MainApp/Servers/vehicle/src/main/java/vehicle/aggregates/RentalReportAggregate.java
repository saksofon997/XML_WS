package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.rentalReportCommands.RentalReportMileageUpdateCommand;
import saga.events.rentalReportEvents.RentalReportMileageUpdateEvent;
import saga.events.rentalReportEvents.RentalReportMileageUpdateFailedEvent;
import vehicle.service.VehicleService;

@Aggregate
public class RentalReportAggregate {
    @AggregateIdentifier
    private String newRentalReportAggregateId;

    @CommandHandler
    public RentalReportAggregate(RentalReportMileageUpdateCommand rentalReportMileageUpdateCommand, VehicleService vehicleService) {
        System.out.println("New rental report -> vehicle mileage update");
        try{
            vehicleService.updateMileage(rentalReportMileageUpdateCommand.getVehicleId(), rentalReportMileageUpdateCommand.getMileage());
            AggregateLifecycle.apply(new RentalReportMileageUpdateEvent(rentalReportMileageUpdateCommand.getNewRentalReportAggregateId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new RentalReportMileageUpdateFailedEvent(rentalReportMileageUpdateCommand.getNewRentalReportAggregateId(),
                    rentalReportMileageUpdateCommand.getRentalReportId(),
                    e.getMessage()));
        }

    }

    @EventSourcingHandler
    protected void on(RentalReportMileageUpdateEvent rentalReportMileageUpdateEvent) {
        this.newRentalReportAggregateId = rentalReportMileageUpdateEvent.getNewRentalReportAggregateId();
    }
}
