package saga.events.vehiclePartsEvents;

import saga.commands.TypeOfCommand;

public class CategoryRollbackEvent {
    private Long categoryId;
    private TypeOfCommand typeOfCommand;

    public CategoryRollbackEvent(){

    }

    public CategoryRollbackEvent(Long categoryId, TypeOfCommand typeOfCommand) {
        this.categoryId = categoryId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
