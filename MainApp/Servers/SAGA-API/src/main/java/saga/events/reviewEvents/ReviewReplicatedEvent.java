package saga.events.reviewEvents;


import saga.commands.TypeOfCommand;

public class ReviewReplicatedEvent {

    private String reviewAggregateId;
    private TypeOfCommand typeOfCommand;

    public ReviewReplicatedEvent() {}

    public ReviewReplicatedEvent(String reviewAggregateId, TypeOfCommand typeOfCommand) {
        this.reviewAggregateId = reviewAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getReviewAggregateId() {
        return reviewAggregateId;
    }

    public void setReviewAggregateId(String reviewAggregateId) {
        this.reviewAggregateId = reviewAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
