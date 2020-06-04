package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehicleOccupancyCommands.MainOccupancyCommand;
import saga.commands.vehicleOccupancyCommands.RollbackOccupancyCommand;
import saga.events.vehicleOccupancyEvents.OccupancyMainEvent;
import saga.events.vehicleOccupancyEvents.OccupancyRollbackEvent;
import vehicle.service.VehicleOccupancyService;

import java.util.UUID;

@Aggregate
public class OccupancyAggregate {
    @AggregateIdentifier
    private String vehicleOccupancyId;

    public OccupancyAggregate() {}

    @CommandHandler
    public OccupancyAggregate(MainOccupancyCommand mainOccupancyCommand) {
        System.out.println("Creating occupancy main event... ");
        AggregateLifecycle.apply(new OccupancyMainEvent(mainOccupancyCommand.getVehicleOccupancyId(),
                mainOccupancyCommand.getVehicleId(),
                mainOccupancyCommand.getOccupancyDTO(),
                mainOccupancyCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(OccupancyMainEvent occupancyMainEvent) {
        System.out.println("Setting occupancy aggregate ID...");
        System.out.println(occupancyMainEvent.getOccupancyDTO());
        this.vehicleOccupancyId = occupancyMainEvent.getVehicleOccupancyId().toString() + "occupancyAggregate" + occupancyMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollbackOccupancyCommand rollbackOccupancyCommand, VehicleOccupancyService occupancyService) {
        System.out.println("Performing rollback for occupancy...");
        try{
            if (rollbackOccupancyCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                occupancyService.delete(rollbackOccupancyCommand.getVehicleId(), rollbackOccupancyCommand.getVehicleOccupancyId());
                System.out.println("Created review event rollbacked -> review deleted.");
            } else if (rollbackOccupancyCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new OccupancyRollbackEvent(rollbackOccupancyCommand.getVehicleOccupancyId(), rollbackOccupancyCommand.getVehicleId(), rollbackOccupancyCommand.getTypeOfCommand()));
    }
}
