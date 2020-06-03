package saga.commands.modelCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;
import saga.dto.ModelDTO;

public class ReplicateModelCommand {
    @TargetAggregateIdentifier
    private String modelAggregateId;

    private Long brandId;
    private Long modelId;
    private ModelDTO modelDTO;
    private TypeOfCommand typeOfCommand;

    public ReplicateModelCommand() {
    }

    public ReplicateModelCommand(String modelAggregateId, Long brandId, Long modelId, ModelDTO modelDTO, TypeOfCommand typeOfCommand) {
        this.modelAggregateId = modelAggregateId;
        this.brandId = brandId;
        this.modelId = modelId;
        this.modelDTO = modelDTO;
        this.typeOfCommand = typeOfCommand;
    }

    public String getModelAggregateId() {
        return modelAggregateId;
    }

    public void setModelAggregateId(String modelAggregateId) {
        this.modelAggregateId = modelAggregateId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public ModelDTO getModelDTO() {
        return modelDTO;
    }

    public void setModelDTO(ModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
