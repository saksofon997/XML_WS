package saga.commands.vehiclePartsCommands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;
import saga.dto.CategoryDTO;

public class MainCategoryCommand {
    @TargetAggregateIdentifier
    private Long categoryId;
    private CategoryDTO categoryDTO;
    private TypeOfCommand typeOfCommand;

    public MainCategoryCommand(){

    }

    public MainCategoryCommand(Long categoryId, CategoryDTO categoryDTO, TypeOfCommand typeOfCommand) {
        this.categoryId = categoryId;
        this.categoryDTO = categoryDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
