package saga.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.BrandDTO;

public class MainBrandCommand {
    @TargetAggregateIdentifier
    private Long brandId;
    private BrandDTO brandDTO;
    private TypeOfCommand typeOfCommand;

    public MainBrandCommand(){

    }

    public MainBrandCommand(Long brandId, BrandDTO brandDTO, TypeOfCommand typeOfCommand) {
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
