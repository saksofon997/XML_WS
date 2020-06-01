package saga.events.priceListEvents;

import saga.commands.TypeOfCommand;

public class PriceListMainFailedEvent {
    private String priceListAggregateId;
    private Long priceListId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public PriceListMainFailedEvent() {}

    public PriceListMainFailedEvent(String priceListAggregateId, Long priceListId, String reason, TypeOfCommand typeOfCommand) {
        this.priceListAggregateId = priceListAggregateId;
        this.priceListId = priceListId;
        this.reason = reason;
        this.typeOfCommand = typeOfCommand;
    }

    public String getPriceListAggregateId() {
        return priceListAggregateId;
    }

    public void setPriceListAggregateId(String priceListAggregateId) {
        this.priceListAggregateId = priceListAggregateId;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
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
