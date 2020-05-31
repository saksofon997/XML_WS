package saga.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BrandReplicatedEvent {

    private String brandAggregateId;

    public BrandReplicatedEvent() {}

    public BrandReplicatedEvent(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
    }

    public String getBrandAggregateId() {
        return brandAggregateId;
    }

    public void setBrandAggregateId(String brandAggregateId) {
        this.brandAggregateId = brandAggregateId;
    }
}
