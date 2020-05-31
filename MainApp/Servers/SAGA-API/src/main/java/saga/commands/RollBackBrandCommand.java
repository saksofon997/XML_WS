package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackBrandCommand {
    @TargetAggregateIdentifier
    private Long brandId;
    private String status;

    public RollBackBrandCommand() {}

    public RollBackBrandCommand(Long brandId, String status) {
        this.brandId = brandId;
        this.status = status;
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
}
