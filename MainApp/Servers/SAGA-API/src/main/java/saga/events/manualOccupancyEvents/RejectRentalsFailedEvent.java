package saga.events.manualOccupancyEvents;

public class RejectRentalsFailedEvent {
    private String occupancyAggregateId;

    private Long occupancyId;
    private String reason;

    public RejectRentalsFailedEvent() {}

    public RejectRentalsFailedEvent(String occupancyAggregateId, Long occupancyId, String reason) {
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
