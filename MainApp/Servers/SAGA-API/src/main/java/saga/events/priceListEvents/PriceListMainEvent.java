package saga.events.priceListEvents;

import saga.commands.TypeOfCommand;
import saga.dto.PricelistDTO;

public class PriceListMainEvent {
    private Long priceListId;
    private PricelistDTO priceListDTO;
    private TypeOfCommand typeOfCommand;

    public PriceListMainEvent() {}

    public PriceListMainEvent(Long priceListId, PricelistDTO priceListDTO, TypeOfCommand typeOfCommand) {
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
