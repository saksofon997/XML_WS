package saga.commands.priceListCommands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.PricelistDTO;

public class MainPriceListCommand {
    @TargetAggregateIdentifier
    private Long priceListId;
    private PricelistDTO priceListDTO;
    private TypeOfCommand typeOfCommand;

    public MainPriceListCommand(){

    }

    public MainPriceListCommand(Long priceListId, PricelistDTO priceListDTO, TypeOfCommand typeOfCommand) {
        this.priceListId = priceListId;
        this.priceListDTO = priceListDTO;
        this.typeOfCommand = typeOfCommand;
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
