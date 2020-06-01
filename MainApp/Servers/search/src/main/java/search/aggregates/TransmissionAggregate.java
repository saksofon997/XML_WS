package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.ReplicateTransmissionCommand;
import saga.events.vehiclePartsEvents.TransmissionReplicatedEvent;
import saga.events.vehiclePartsEvents.TransmissionReplicatedFailedEvent;
import search.service.TransmissionService;

@Aggregate
public class TransmissionAggregate {
    @AggregateIdentifier
    private String transmissionAggregateId;

    @CommandHandler
    public TransmissionAggregate(ReplicateTransmissionCommand replicateTransmissionCommand, TransmissionService transmissionService) {
        System.out.println("USO SAM U SEARCH TRANSMISSION");
        try{
            if(replicateTransmissionCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                transmissionService.add(replicateTransmissionCommand.getTransmissionDTO());
                AggregateLifecycle.apply(new TransmissionReplicatedEvent(replicateTransmissionCommand.getTransmissionAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateTransmissionCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                transmissionService.update(replicateTransmissionCommand.getTransmissionId(),replicateTransmissionCommand.getTransmissionDTO());
                AggregateLifecycle.apply(new TransmissionReplicatedEvent(replicateTransmissionCommand.getTransmissionAggregateId(), TypeOfCommand.UPDATE));
            } else {
                transmissionService.delete(replicateTransmissionCommand.getTransmissionId());
                AggregateLifecycle.apply(new TransmissionReplicatedEvent(replicateTransmissionCommand.getTransmissionAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new TransmissionReplicatedFailedEvent(replicateTransmissionCommand.getTransmissionAggregateId(),
                    replicateTransmissionCommand.getTransmissionId(),
                    e.getMessage(), replicateTransmissionCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(TransmissionReplicatedEvent transmissionReplicatedEvent) {
        this.transmissionAggregateId = transmissionReplicatedEvent.getTransmissionAggregateId();
    }

    @EventSourcingHandler
    protected void on(TransmissionReplicatedFailedEvent transmissionReplicatedFailedEvent) {
        this.transmissionAggregateId = transmissionReplicatedFailedEvent.getTransmissionAggregateId();
    }
}
