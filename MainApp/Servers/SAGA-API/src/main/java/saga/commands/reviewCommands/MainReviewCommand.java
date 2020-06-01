package saga.commands.reviewCommands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.ReviewDTO;

public class MainReviewCommand {
    @TargetAggregateIdentifier
    private Long reviewId;
    private Long vehicleId;
    private ReviewDTO reviewDTO;
    private TypeOfCommand typeOfCommand;

    public MainReviewCommand(){

    }

    public MainReviewCommand(Long reviewId, Long vehicleId, ReviewDTO reviewDTO, TypeOfCommand typeOfCommand) {
        this.reviewId = reviewId;
        this.vehicleId = vehicleId;
        this.reviewDTO = reviewDTO;
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

    public ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
