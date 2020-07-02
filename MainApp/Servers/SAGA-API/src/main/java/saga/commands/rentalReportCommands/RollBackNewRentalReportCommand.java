package saga.commands.rentalReportCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackNewRentalReportCommand {

    @TargetAggregateIdentifier
    private Long rentalReportId;

    public RollBackNewRentalReportCommand() {
    }

    public RollBackNewRentalReportCommand(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }

    public Long getRentalReportId() {
        return rentalReportId;
    }

    public void setRentalReportId(Long rentalReportId) {
        this.rentalReportId = rentalReportId;
    }
}
