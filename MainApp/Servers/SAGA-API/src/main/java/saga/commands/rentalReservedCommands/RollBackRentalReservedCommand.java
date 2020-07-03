package saga.commands.rentalReservedCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackRentalReservedCommand {
    @TargetAggregateIdentifier
    private Long rentalId;
    private String status;

    public RollBackRentalReservedCommand() {
    }

    public RollBackRentalReservedCommand(Long rentalId, String status) {
        this.rentalId = rentalId;
        this.status = status;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
