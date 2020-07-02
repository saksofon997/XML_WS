package saga.events.rentalReservedEvents;

public class RentalVehicleOccupancyAddFailedEvent {
    private String rentalReservedAggregateId;

    private Long rentalId;
    private String reason;

    public RentalVehicleOccupancyAddFailedEvent() {
    }

    public RentalVehicleOccupancyAddFailedEvent(String rentalReservedAggregateId, Long rentalId, String reason) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
        this.rentalId = rentalId;
        this.reason = reason;
    }

    public String getRentalReservedAggregateId() {
        return rentalReservedAggregateId;
    }

    public void setRentalReservedAggregateId(String rentalReservedAggregateId) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
