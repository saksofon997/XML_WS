package saga.events.rentalReportEvents;

public class NewRentalReportEvent {
    private Long rentalReportId;
    private Long vehicleId;
    private double mileage;

    public NewRentalReportEvent() {
    }

    public NewRentalReportEvent(Long rentalReportId, Long vehicleId, double mileage) {
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
