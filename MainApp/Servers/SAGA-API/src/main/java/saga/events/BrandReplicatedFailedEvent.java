package saga.events;

import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;

public class BrandReplicatedFailedEvent {
    private String brandAggregateId;

    private Long brandId;
    private String reason;
    private TypeOfCommand typeOfCommand;

    public BrandReplicatedFailedEvent() {}

    public BrandReplicatedFailedEvent(String brandAggregateId, Long brandId, String reason, TypeOfCommand typeOfCommand) {
        this.brandAggregateId = brandAggregateId;
        this.brandId = brandId;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TypeOfCommand getTypeOfCommand() {
        return typeOfCommand;
    }

    public void setTypeOfCommand(TypeOfCommand typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
