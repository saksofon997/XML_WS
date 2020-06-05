package location.model;

public class LocationMessage {

    private Long vehicleId;
    private String message;

    public LocationMessage() {
    }

    public LocationMessage(Long vehicleId, String message) {
        this.vehicleId = vehicleId;
        this.message = message;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
