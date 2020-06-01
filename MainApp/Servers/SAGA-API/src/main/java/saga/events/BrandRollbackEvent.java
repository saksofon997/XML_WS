package saga.events;

import saga.commands.TypeOfCommand;

public class BrandRollbackEvent {
    private Long brandId;
    private TypeOfCommand typeOfCommand;

    public BrandRollbackEvent(){

    }

    public BrandRollbackEvent(Long brandId, TypeOfCommand typeOfCommand) {
        this.brandId = brandId;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
