package saga.commands.rentalReportCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackNewRentalReportCommand {

    @TargetAggregateIdentifier
    private Long rentalReportId;
    private String status;

    public RollBackNewRentalReportCommand() {
    }

    public RollBackNewRentalReportCommand(Long rentalReportId, String status) {
        this.rentalReportId = rentalReportId;
        this.status = status;
    }

    public Long getRentalReportId() {
        return rentalReportId;
    }

    public void setRentalReportId(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
