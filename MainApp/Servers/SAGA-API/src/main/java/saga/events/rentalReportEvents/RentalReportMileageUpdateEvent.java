package saga.events.rentalReportEvents;

public class RentalReportMileageUpdateEvent {
    private String newRentalReportAggregateId;

    public RentalReportMileageUpdateEvent() {
    }

    public RentalReportMileageUpdateEvent(String newRentalReportAggregateId) {
        this.newRentalReportAggregateId = newRentalReportAggregateId;
    }

    public String getNewRentalReportAggregateId() {
        return newRentalReportAggregateId;
    }

    public void setNewRentalReportAggregateId(String newRentalReportAggregateId) {
        this.newRentalReportAggregateId = newRentalReportAggregateId;
    }
}
