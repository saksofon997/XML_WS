package saga.commands.rentalReportCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RentalReportMileageUpdateCommand {
    @TargetAggregateIdentifier
    private String newRentalReportAggregateId;

    private Long rentalReportId;
    private Long vehicleId;
    private double mileage;

    public RentalReportMileageUpdateCommand() {
    }

    public RentalReportMileageUpdateCommand(String newRentalReportAggregateId, Long rentalReportId, Long vehicleId, double mileage) {
        this.newRentalReportAggregateId = newRentalReportAggregateId;
        this.rentalReportId = rentalReportId;
        this.vehicleId = vehicleId;
        this.mileage = mileage;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
