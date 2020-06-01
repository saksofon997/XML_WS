package saga.events.reviewEvents;

import saga.commands.TypeOfCommand;

public class ReviewMainFailedEvent {
    private String reviewAggregateId;
    private Long reviewId;
    private Long vehicleId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public ReviewMainFailedEvent() {}

    public ReviewMainFailedEvent(String reviewAggregateId, Long reviewId, Long vehicleId, String reason, TypeOfCommand typeOfCommand) {
        this.reviewAggregateId = reviewAggregateId;
        this.reviewId = reviewId;
        this.vehicleId = vehicleId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }


    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getReviewAggregateId() {
        return reviewAggregateId;
    }

    public void setReviewAggregateId(String reviewAggregateId) {
        this.reviewAggregateId = reviewAggregateId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
