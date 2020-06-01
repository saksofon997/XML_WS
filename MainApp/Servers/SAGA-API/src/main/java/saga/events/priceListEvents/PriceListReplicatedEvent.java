package saga.events.priceListEvents;


import saga.commands.TypeOfCommand;

public class PriceListReplicatedEvent {

    private String priceListAggregateId;
    private TypeOfCommand typeOfCommand;

    public PriceListReplicatedEvent() {}

    public PriceListReplicatedEvent(String priceListAggregateId, TypeOfCommand typeOfCommand) {
        this.priceListAggregateId = priceListAggregateId;
        this.typeOfCommand = typeOfCommand;
    }

    public String getPriceListAggregateId() {
        return priceListAggregateId;
    }

    public void setPriceListAggregateId(String priceListAggregateId) {
        this.priceListAggregateId = priceListAggregateId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
