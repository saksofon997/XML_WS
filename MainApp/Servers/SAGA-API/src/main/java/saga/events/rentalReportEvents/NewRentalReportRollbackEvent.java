package saga.events.rentalReportEvents;

public class NewRentalReportRollbackEvent {
    private Long rentalReportId;

    public NewRentalReportRollbackEvent() {
    }

    public NewRentalReportRollbackEvent(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }

    public Long getRentalReportId() {
        return rentalReportId;
    }

    public void setRentalReportId(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }
}
