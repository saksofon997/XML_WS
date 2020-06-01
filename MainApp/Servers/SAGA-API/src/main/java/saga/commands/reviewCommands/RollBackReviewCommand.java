package saga.commands.reviewCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollBackReviewCommand {
    @TargetAggregateIdentifier
    private Long reviewId;
    private Long vehicleId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackReviewCommand() {}

    public RollBackReviewCommand(Long reviewId, Long vehicleId, String status, TypeOfCommand typeOfCommand) {
        this.reviewId = reviewId;
        this.vehicleId = vehicleId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
