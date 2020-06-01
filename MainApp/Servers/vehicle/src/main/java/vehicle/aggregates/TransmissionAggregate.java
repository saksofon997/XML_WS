package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainTransmissionCommand;
import saga.commands.vehiclePartsCommands.RollBackTransmissionCommand;
import saga.events.vehiclePartsEvents.TransmissionMainEvent;
import saga.events.vehiclePartsEvents.TransmissionRollbackEvent;
import vehicle.service.TransmissionService;

import java.util.UUID;

@Aggregate
public class TransmissionAggregate {

    @AggregateIdentifier
    private String transmissionId;

    public TransmissionAggregate() {}

    @CommandHandler
    public TransmissionAggregate(MainTransmissionCommand mainTransmissionCommand) {
        System.out.println("Creating transmission main event... ");
        AggregateLifecycle.apply(new TransmissionMainEvent(mainTransmissionCommand.getTransmissionId(),
                mainTransmissionCommand.getTransmissionDTO(),
                mainTransmissionCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(TransmissionMainEvent transmissionMainEvent) {
        System.out.println("Setting transmission aggregate ID...");
        System.out.println(transmissionMainEvent.getTransmissionDTO());
        this.transmissionId = transmissionMainEvent.getTransmissionDTO().toString() + "transmissionAggregate" + transmissionMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackTransmissionCommand rollbackTransmissionCommand, TransmissionService transmissionService) {
        System.out.println("Performing rollback for transmission...");
        try{
            if (rollbackTransmissionCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                transmissionService.delete(rollbackTransmissionCommand.getTransmissionId());
                System.out.println("Created transmission event rollbacked -> transmission deleted.");
            } else if (rollbackTransmissionCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new TransmissionRollbackEvent(rollbackTransmissionCommand.getTransmissionId(), rollbackTransmissionCommand.getTypeOfCommand()));
    }
}
