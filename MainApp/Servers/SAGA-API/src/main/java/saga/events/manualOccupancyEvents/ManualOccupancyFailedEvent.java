package saga.events.manualOccupancyEvents;


public class ManualOccupancyFailedEvent {
    private String occupancyAggregateId;
    private Long occupancyId;
    private String reason;

    public ManualOccupancyFailedEvent() {}

    public ManualOccupancyFailedEvent(String occupancyAggregateId, Long occupancyId, String reason) {
        this.occupancyAggregateId = occupancyAggregateId;
        this.occupancyId = occupancyId;
        this.reason = reason;
    }

    public String getOccupancyAggregateId() {
        return occupancyAggregateId;
    }

    public void setOccupancyAggregateId(String occupancyAggregateId) {
        this.occupancyAggregateId = occupancyAggregateId;
    }

    public Long getOccupancyId() {
        return occupancyId;
    }

    public void setOccupancyId(Long occupancyId) {
        this.occupancyId = occupancyId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
