package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RollbackVehicleCommand {
    @TargetAggregateIdentifier
    private Long vehicleId;
    private String status;

    public RollbackVehicleCommand() {
    }

    public RollbackVehicleCommand(Long vehicleId, String status) {
        this.vehicleId = vehicleId;
        this.status = status;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
