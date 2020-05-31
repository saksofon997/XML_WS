package saga.events;

public class BrandCreatedFailedEvent {
    private String brandAggregateId;
    private Long brandId;
    private String reason;

    public BrandCreatedFailedEvent() {}

    public BrandCreatedFailedEvent(String brandAggregateId, Long brandId, String reason) {
        this.brandAggregateId = brandAggregateId;
        this.brandId = brandId;
        this.reason = reason;
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
}
