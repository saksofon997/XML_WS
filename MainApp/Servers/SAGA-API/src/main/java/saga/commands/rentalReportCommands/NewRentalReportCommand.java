package saga.commands.rentalReportCommands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class NewRentalReportCommand {
    @TargetAggregateIdentifier
    private Long rentalReportId;
    private Long vehicleId;
    private double mileage;

    public NewRentalReportCommand() {
    }

    public NewRentalReportCommand(Long rentalReportId, Long vehicleId, double mileage) {
        this.rentalReportId = rentalReportId;
        this.vehicleId = vehicleId;
        this.mileage = mileage;
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
