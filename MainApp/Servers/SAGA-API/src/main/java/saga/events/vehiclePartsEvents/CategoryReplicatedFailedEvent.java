package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class CategoryReplicatedFailedEvent {
    private String categoryAggregateId;

    private Long categoryId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public CategoryReplicatedFailedEvent() {}

    public CategoryReplicatedFailedEvent(String categoryAggregateId, Long categoryId, String reason, TypeOfCommand typeOfCommand) {
        this.categoryAggregateId = categoryAggregateId;
        this.categoryId = categoryId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getCategoryAggregateId() {
        return categoryAggregateId;
    }

    public void setCategoryAggregateId(String categoryAggregateId) {
        this.categoryAggregateId = categoryAggregateId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
