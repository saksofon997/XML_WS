package saga.events.reviewEvents;

import saga.commands.TypeOfCommand;

public class ReviewRollbackEvent {
    private Long reviewId;
    private Long vehicleId;
    private TypeOfCommand typeOfCommand;

    public ReviewRollbackEvent(){

    }

    public ReviewRollbackEvent(Long reviewId, Long vehicleId, TypeOfCommand typeOfCommand) {
        this.reviewId = reviewId;
        this.vehicleId = vehicleId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
