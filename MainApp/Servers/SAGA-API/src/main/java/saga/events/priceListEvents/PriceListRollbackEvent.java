package saga.events.priceListEvents;

import saga.commands.TypeOfCommand;

public class PriceListRollbackEvent {
    private Long priceListId;
    private TypeOfCommand typeOfCommand;

    public PriceListRollbackEvent(){

    }

    public PriceListRollbackEvent(Long priceListId, TypeOfCommand typeOfCommand) {
        this.priceListId = priceListId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
