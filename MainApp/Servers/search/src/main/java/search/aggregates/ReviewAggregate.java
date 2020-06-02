package search.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.reviewCommands.ReplicateReviewCommand;
import saga.commands.vehiclePartsCommands.ReplicateFuelCommand;
import saga.events.reviewEvents.ReviewReplicatedEvent;
import saga.events.reviewEvents.ReviewReplicatedFailedEvent;
import saga.events.vehiclePartsEvents.FuelReplicatedEvent;
import saga.events.vehiclePartsEvents.FuelReplicatedFailedEvent;
import search.service.FuelService;
import search.service.ReviewService;

@Aggregate
public class ReviewAggregate {
    @AggregateIdentifier
    private String reviewAggregateId;

    @CommandHandler
    public ReviewAggregate(ReplicateReviewCommand replicateReviewCommand, ReviewService reviewService) {
        System.out.println("USO SAM U SEARCH REVIEW");
        try{
            if(replicateReviewCommand.getTypeOfCommand() == TypeOfCommand.CREATE){
                reviewService.add(replicateReviewCommand.getVehicleId(),replicateReviewCommand.getReviewDTO() );
                AggregateLifecycle.apply(new ReviewReplicatedEvent(replicateReviewCommand.getReviewAggregateId(), TypeOfCommand.CREATE));
            } else if (replicateReviewCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                reviewService.update(replicateReviewCommand.getVehicleId(), replicateReviewCommand.getReviewId(),replicateReviewCommand.getReviewDTO());
                AggregateLifecycle.apply(new ReviewReplicatedEvent(replicateReviewCommand.getReviewAggregateId(), TypeOfCommand.UPDATE));
            } else {
                reviewService.delete(replicateReviewCommand.getVehicleId(), replicateReviewCommand.getReviewId());
                AggregateLifecycle.apply(new ReviewReplicatedEvent(replicateReviewCommand.getReviewAggregateId(), TypeOfCommand.DELETE));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            AggregateLifecycle.apply(new ReviewReplicatedFailedEvent(replicateReviewCommand.getReviewAggregateId(),
                    replicateReviewCommand.getReviewId(),
                    replicateReviewCommand.getVehicleId(),
                    e.getMessage(), replicateReviewCommand.getTypeOfCommand()));
        }

    }

    @EventSourcingHandler
    protected void on(ReviewReplicatedEvent reviewReplicatedEvent) {
        this.reviewAggregateId = reviewReplicatedEvent.getReviewAggregateId();
    }

    @EventSourcingHandler
    protected void on(ReviewReplicatedFailedEvent reviewReplicatedFailedEvent) {
        this.reviewAggregateId = reviewReplicatedFailedEvent.getReviewAggregateId();
    }
}
