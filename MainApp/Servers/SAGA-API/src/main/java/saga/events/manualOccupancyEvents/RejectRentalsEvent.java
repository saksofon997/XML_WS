package saga.events.manualOccupancyEvents;

public class RejectRentalsEvent {
    private String occupancyAggregateId;

    public RejectRentalsEvent() {}

    public RejectRentalsEvent(String occupancyAggregateId) {
        this.occupancyAggregateId = occupancyAggregateId;
    }

    public String getOccupancyAggregateId() {
        return occupancyAggregateId;
    }

    public void setOccupancyAggregateId(String occupancyAggregateId) {
        this.occupancyAggregateId = occupancyAggregateId;
    }
}
