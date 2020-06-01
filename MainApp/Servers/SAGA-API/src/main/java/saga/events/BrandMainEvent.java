package saga.events;

import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;

public class BrandMainEvent {
    private Long brandId;
    private BrandDTO brandDTO;
    private TypeOfCommand typeOfCommand;

    public BrandMainEvent() {}

    public BrandMainEvent(Long brandId, BrandDTO brandDTO, TypeOfCommand typeOfCommand) {
        this.brandId = brandId;
        this.brandDTO = brandDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public BrandDTO getBrandDTO() {
        return brandDTO;
    }

    public void setBrandDTO(BrandDTO brandDTO) {
        this.brandDTO = brandDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
