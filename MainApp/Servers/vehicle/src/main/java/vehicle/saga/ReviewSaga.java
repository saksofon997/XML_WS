package vehicle.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import saga.commands.TypeOfCommand;
import saga.commands.reviewCommands.ReplicateReviewCommand;
import saga.commands.reviewCommands.RollBackReviewCommand;
import saga.events.reviewEvents.ReviewMainEvent;
import saga.events.reviewEvents.ReviewReplicatedEvent;
import saga.events.reviewEvents.ReviewReplicatedFailedEvent;
import saga.events.reviewEvents.ReviewRollbackEvent;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class ReviewSaga {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "reviewId")
    public void handle(ReviewMainEvent reviewMainEvent) {
        System.out.println("Saga invoked for review.");

        String reviewAggregateId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("reviewAggregateId", reviewAggregateId);

        commandGateway.send(new ReplicateReviewCommand(reviewAggregateId, reviewMainEvent.getReviewId(),
                reviewMainEvent.getVehicleId(),
                reviewMainEvent.getReviewDTO(), reviewMainEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "reviewAggregateId")
    public void handle(ReviewReplicatedEvent reviewReplicatedEvent) {
        String message = "Saga finishing... \n";
        if (reviewReplicatedEvent.getTypeOfCommand() == TypeOfCommand.CREATE) {
            System.out.println(message + "Review created and replicated successfully!");
        } else if (reviewReplicatedEvent.getTypeOfCommand() == TypeOfCommand.UPDATE) {
            System.out.println(message + "Review updated and update replicated successfully!");
        } else {
            System.out.println(message + "Review deleted successfully!");
        }

        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "reviewAggregateId")
    public void handle(ReviewReplicatedFailedEvent reviewReplicatedFailedEvent) {
        System.out.println("Saga declined, starting compensation transaction!");

        commandGateway.send(new RollBackReviewCommand(reviewReplicatedFailedEvent.getReviewId(),
                reviewReplicatedFailedEvent.getVehicleId(),
                reviewReplicatedFailedEvent.getReason(),
                reviewReplicatedFailedEvent.getTypeOfCommand()));
    }

    @SagaEventHandler(associationProperty = "reviewId")
    public void handle(ReviewRollbackEvent reviewRollbackEvent) {
        System.out.println("Saga finishing!");
        SagaLifecycle.end();
    }
}
