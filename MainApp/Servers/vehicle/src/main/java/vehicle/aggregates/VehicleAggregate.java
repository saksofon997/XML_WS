package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.CreateVehicleCommand;
import saga.commands.RollBackBrandCommand;
import saga.commands.RollbackVehicleCommand;
import saga.events.BrandRollbackEvent;
import saga.events.VehicleCreatedEvent;
import saga.events.VehicleRollbackEvent;
import vehicle.service.BrandService;
import vehicle.service.VehicleService;

@Aggregate
public class VehicleAggregate {
    @AggregateIdentifier
    private String vehicleId;

    public VehicleAggregate() {}

    @CommandHandler
    public VehicleAggregate(CreateVehicleCommand createvehicleCommand) {
        System.out.println("ODJE SAM ");
        AggregateLifecycle.apply(new VehicleCreatedEvent(createvehicleCommand.getVehicleId(),createvehicleCommand.getVehicleDTO()));
    }

    @EventSourcingHandler
    public void on(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("ODJE SAM 1");
        System.out.println(vehicleCreatedEvent);
        this.vehicleId = vehicleCreatedEvent.getVehicleId().toString() + "vehicleAggregate";
    }

    @CommandHandler
    public void on(RollbackVehicleCommand rollbackVehicleCommand, VehicleService vehicleService) {
        //brandService.update(rollbackOrderCommand.getBrandId(), OrderStatus.REJECTED);
        System.out.println("ROLBACKOVANJE");
        AggregateLifecycle.apply(new VehicleRollbackEvent(rollbackVehicleCommand.getVehicleId()));
    }
}
