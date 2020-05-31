package saga.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.BrandDTO;

public class BrandCreatedEvent {
    @TargetAggregateIdentifier
    private String brandAggregateId;

    public BrandCreatedEvent() {}

    public BrandCreatedEvent(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
    }

    public String getBrandAggregateId() {
        return brandAggregateId;
    }

    public void setBrandAggregateId(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
    }
}
