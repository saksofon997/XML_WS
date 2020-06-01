package saga.commands.priceListCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollBackPriceListCommand {
    @TargetAggregateIdentifier
    private Long priceListId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackPriceListCommand() {}

    public RollBackPriceListCommand(Long priceListId, String status, TypeOfCommand typeOfCommand) {
        this.priceListId = priceListId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
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
