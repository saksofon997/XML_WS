package saga.events.vehiclePartsEvents;


import saga.commands.TypeOfCommand;

public class CategoryReplicatedEvent {

    private String categoryAggregateId;
    private TypeOfCommand typeOfCommand;

    public CategoryReplicatedEvent() {}

    public CategoryReplicatedEvent(String categoryAggregateId, TypeOfCommand typeOfCommand) {
        this.categoryAggregateId = categoryAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getCategoryAggregateId() {
        return categoryAggregateId;
    }

    public void setCategoryAggregateId(String categoryAggregateId) {
        this.categoryAggregateId = categoryAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
