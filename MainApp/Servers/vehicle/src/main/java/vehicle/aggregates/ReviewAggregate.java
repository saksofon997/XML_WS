package vehicle.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import saga.commands.TypeOfCommand;
import saga.commands.reviewCommands.MainReviewCommand;
import saga.commands.reviewCommands.RollBackReviewCommand;
import saga.events.reviewEvents.ReviewMainEvent;
import saga.events.reviewEvents.ReviewRollbackEvent;
import vehicle.service.ReviewService;

import java.util.UUID;

@Aggregate
public class ReviewAggregate {

    @AggregateIdentifier
    private String reviewId;

    public ReviewAggregate() {}

    @CommandHandler
    public ReviewAggregate(MainReviewCommand mainReviewCommand) {
        System.out.println("Creating review main event... ");
        AggregateLifecycle.apply(new ReviewMainEvent(mainReviewCommand.getReviewId(),
                mainReviewCommand.getVehicleId(),
                mainReviewCommand.getReviewDTO(),
                mainReviewCommand.getTypeOfCommand()));
    }

    @EventSourcingHandler
    public void on(ReviewMainEvent reviewMainEvent) {
        System.out.println("Setting review aggregate ID...");
        System.out.println(reviewMainEvent.getReviewDTO());
        this.reviewId = reviewMainEvent.getReviewId().toString() + "reviewAggregate" + reviewMainEvent.getTypeOfCommand() + UUID.randomUUID().toString();
    }

    @CommandHandler
    public void on(RollBackReviewCommand rollbackReviewCommand, ReviewService reviewService) {
        System.out.println("Performing rollback for review...");
        try{
            if (rollbackReviewCommand.getTypeOfCommand() == TypeOfCommand.CREATE) {
                reviewService.delete(rollbackReviewCommand.getVehicleId(), rollbackReviewCommand.getReviewId());
                System.out.println("Created review event rollbacked -> review deleted.");
            } else if (rollbackReviewCommand.getTypeOfCommand() == TypeOfCommand.UPDATE) {
                System.out.println("Ovo ne znam kako da pamtim staro stanje...");
            } else {
                System.out.println("Ovo ne znam kako dobijem iz baze onaj koji je status deleted...");
            }
        } catch (Exception e) {
            System.out.println("Neuspesan rollback..." + e.getMessage());
        }
        AggregateLifecycle.apply(new ReviewRollbackEvent(rollbackReviewCommand.getReviewId(), rollbackReviewCommand.getVehicleId(), rollbackReviewCommand.getTypeOfCommand()));
    }
}
