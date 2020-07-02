package saga.events.rentalReservedEvents;

public class RentalVehicleOccupancyAddEvent {
    private String rentalReservedAggregateId;

    public RentalVehicleOccupancyAddEvent() {
    }

    public RentalVehicleOccupancyAddEvent(String rentalReservedAggregateId) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
    }

    public String getRentalReservedAggregateId() {
        return rentalReservedAggregateId;
    }

    public void setRentalReservedAggregateId(String rentalReservedAggregateId) {
        this.rentalReservedAggregateId = rentalReservedAggregateId;
    }
}
