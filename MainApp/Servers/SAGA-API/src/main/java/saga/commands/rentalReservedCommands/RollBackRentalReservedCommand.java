package saga.commands.rentalReservedCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollBackRentalReservedCommand {
    @TargetAggregateIdentifier
    private Long rentalId;

    public RollBackRentalReservedCommand() {
    }

    public RollBackRentalReservedCommand(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}
