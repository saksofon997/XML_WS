package saga.commands.priceListCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.PricelistDTO;

public class ReplicatePriceListCommand {
    @TargetAggregateIdentifier
    private String priceListAggregateId;

    private Long priceListId;
    private PricelistDTO priceListDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicatePriceListCommand(){

    }

    public ReplicatePriceListCommand(String priceListAggregateId, Long priceListId, PricelistDTO priceListDTO, TypeOfCommand typeOfCommand) {
        this.priceListAggregateId = priceListAggregateId;
        this.priceListId = priceListId;
        this.priceListDTO = priceListDTO;
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

    public PricelistDTO getPriceListDTO() {
        return priceListDTO;
    }

    public void setPriceListDTO(PricelistDTO priceListDTO) {
        this.priceListDTO = priceListDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
