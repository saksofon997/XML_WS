package saga.events;

import saga.commands.TypeOfCommand;

public class BrandReplicatedEvent {

    private String brandAggregateId;
    private TypeOfCommand typeOfCommand;

    public BrandReplicatedEvent() {}

    public BrandReplicatedEvent(String brandAggregateId, TypeOfCommand typeOfCommand) {
        this.brandAggregateId = brandAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getBrandAggregateId() {
        return brandAggregateId;
    }

    public void setBrandAggregateId(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
