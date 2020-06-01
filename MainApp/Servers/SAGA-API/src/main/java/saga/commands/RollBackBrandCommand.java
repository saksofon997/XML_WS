package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackBrandCommand {
    @TargetAggregateIdentifier
    private Long brandId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackBrandCommand() {}

    public RollBackBrandCommand(Long brandId, String status, TypeOfCommand typeOfCommand) {
        this.brandId = brandId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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
