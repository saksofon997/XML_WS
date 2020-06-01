package saga.commands.vehiclePartsCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;
import saga.dto.CategoryDTO;

public class ReplicateCategoryCommand {
    @TargetAggregateIdentifier
    private String categoryAggregateId;

    private Long categoryId;
    private CategoryDTO categoryDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateCategoryCommand(){

    }

    public ReplicateCategoryCommand(String categoryAggregateId, Long categoryId, CategoryDTO categoryDTO, TypeOfCommand typeOfCommand) {
        this.categoryAggregateId = categoryAggregateId;
        this.categoryId = categoryId;
        this.categoryDTO = categoryDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public String getCategoryAggregateId() {
        return categoryAggregateId;
    }

    public void setCategoryAggregateId(String categoryAggregateId) {
        this.categoryAggregateId = categoryAggregateId;
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
