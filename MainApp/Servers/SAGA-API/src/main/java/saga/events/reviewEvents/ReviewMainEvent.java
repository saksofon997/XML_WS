package saga.events.reviewEvents;

import saga.commands.TypeOfCommand;
import saga.dto.ReviewDTO;

public class ReviewMainEvent {
    private Long reviewId;
    private Long vehicleId;
    private ReviewDTO reviewDTO;
    private TypeOfCommand typeOfCommand;

    public ReviewMainEvent() {}


    public ReviewMainEvent(Long reviewId, Long vehicleId, ReviewDTO reviewDTO, TypeOfCommand typeOfCommand) {
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
