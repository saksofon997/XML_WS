package saga.events.modelEvents;

import saga.commands.TypeOfCommand;
import saga.dto.ModelDTO;

public class ModelMainEvent {
    private Long modelId;
    private ModelDTO modelDTO;
    private TypeOfCommand typeOfCommand;

    public ModelMainEvent() {
    }

    public ModelMainEvent(Long modelId, ModelDTO modelDTO, TypeOfCommand typeOfCommand) {
        this.modelId = modelId;
        this.modelDTO = modelDTO;
        this.typeOfCommand = typeOfCommand;
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
