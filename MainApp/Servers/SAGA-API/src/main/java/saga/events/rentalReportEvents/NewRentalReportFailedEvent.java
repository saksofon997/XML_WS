package saga.events.rentalReportEvents;

public class NewRentalReportFailedEvent {
    private String newRentalReportAggregateId;
    private Long rentalReportId;
    private String reason;

    public NewRentalReportFailedEvent() {
    }

    public NewRentalReportFailedEvent(String newRentalReportAggregateId, Long rentalReportId, String reason) {
        this.newRentalReportAggregateId = newRentalReportAggregateId;
        this.rentalReportId = rentalReportId;
        this.reason = reason;
    }

    public String getNewRentalReportAggregateId() {
        return newRentalReportAggregateId;
    }

    public void setNewRentalReportAggregateId(String newRentalReportAggregateId) {
        this.newRentalReportAggregateId = newRentalReportAggregateId;
    }

    public Long getRentalReportId() {
        return rentalReportId;
    }

    public void setRentalReportId(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
