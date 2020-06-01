package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.MainVehicleCommand;
import saga.commands.RollbackVehicleCommand;
import saga.commands.TypeOfCommand;
import saga.events.VehicleMainEvent;
import saga.events.VehicleRollbackEvent;
import vehicle.service.VehicleService;

import java.util.UUID;

@Aggregate
public class VehicleAggregate {
    @AggregateIdentifier
    private String vehicleId;

    public VehicleAggregate() {}

    @CommandHandler
    public VehicleAggregate(MainVehicleCommand mainvehicleCommand) {
        System.out.println("Creating vehicle main event...");
        AggregateLifecycle.apply(new VehicleMainEvent(mainvehicleCommand.getVehicleId(),mainvehicleCommand.getVehicleDTO(), mainvehicleCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(VehicleMainEvent vehicleMainEvent) {
        System.out.println("Setting vehicle aggregate ID...");
        System.out.println(vehicleMainEvent);
        this.vehicleId = vehicleMainEvent.getVehicleId().toString() + "vehicleAggregate" + vehicleMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollbackVehicleCommand rollbackVehicleCommand, VehicleService vehicleService) {
        //brandService.update(rollbackOrderCommand.getBrandId(), OrderStatus.REJECTED);
        System.out.println("Performing rollback for vehicle...");
        try{
            if (rollbackVehicleCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                vehicleService.deletePermanent(rollbackVehicleCommand.getVehicleId());
                System.out.println("Created vehicle event rollbacked -> vehicle deleted.");
            } else if (rollbackVehicleCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }

        AggregateLifecycle.apply(new VehicleRollbackEvent(rollbackVehicleCommand.getVehicleId(), rollbackVehicleCommand.getTypeOfCommand()));
    }
}
