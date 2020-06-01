package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;

public class RollBackCategoryCommand {
    @TargetAggregateIdentifier
    private Long categoryId;
    private String status;
    private TypeOfCommand typeOfCommand;

    public RollBackCategoryCommand() {}

    public RollBackCategoryCommand(Long categoryId, String status, TypeOfCommand typeOfCommand) {
        this.categoryId = categoryId;
        this.status = status;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
