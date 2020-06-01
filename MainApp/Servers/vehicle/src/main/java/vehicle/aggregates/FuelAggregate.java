package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainFuelCommand;
import saga.commands.vehiclePartsCommands.RollBackFuelCommand;
import saga.events.vehiclePartsEvents.FuelMainEvent;
import saga.events.vehiclePartsEvents.FuelRollbackEvent;
import vehicle.service.FuelService;

import java.util.UUID;

@Aggregate
public class FuelAggregate {

    @AggregateIdentifier
    private String fuelId;

    public FuelAggregate() {}

    @CommandHandler
    public FuelAggregate(MainFuelCommand mainFuelCommand) {
        System.out.println("Creating fuel main event... ");
        AggregateLifecycle.apply(new FuelMainEvent(mainFuelCommand.getFuelId(),mainFuelCommand.getFuelDTO(), mainFuelCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(FuelMainEvent fuelMainEvent) {
        System.out.println("Setting fuel aggregate ID...");
        System.out.println(fuelMainEvent.getFuelDTO());
        this.fuelId = fuelMainEvent.getFuelId().toString() + "fuelAggregate" + fuelMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackFuelCommand rollbackFuelCommand, FuelService fuelService) {
        System.out.println("Performing rollback for fuel...");
        try{
            if (rollbackFuelCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                fuelService.delete(rollbackFuelCommand.getFuelId());
                System.out.println("Created fuel event rollbacked -> fuel deleted.");
            } else if (rollbackFuelCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new FuelRollbackEvent(rollbackFuelCommand.getFuelId(), rollbackFuelCommand.getTypeOfCommand()));
    }
}
