package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.BrandDTO;

public class ReplicateBrandCommand {
    @TargetAggregateIdentifier
    private String brandAggregateId;

    private Long brandId;
    private BrandDTO brandDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateBrandCommand(){

    }

    public ReplicateBrandCommand(String brandAggregateId, Long brandId, BrandDTO brandDTO, TypeOfCommand typeOfCommand) {
        this.brandAggregateId = brandAggregateId;
        this.brandId = brandId;
        this.brandDTO = brandDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public String getBrandAggregateId() {
        return brandAggregateId;
    }

    public void setBrandAggregateId(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
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
