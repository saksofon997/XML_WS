package saga.events.rentalReservedEvents;

public class RentalReservedRollbackEvent {
    private Long rentalId;

    public RentalReservedRollbackEvent() {
    }

    public RentalReservedRollbackEvent(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}
