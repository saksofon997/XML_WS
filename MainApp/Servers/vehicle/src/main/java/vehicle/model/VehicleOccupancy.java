package vehicle.model;

import org.joda.time.DateTime;

public class VehicleOccupancy {

    private String id;
    private DateTime startTime;
    private DateTime endTime;
    private String type;
    private String locations;

    public VehicleOccupancy(String id, DateTime startTime, DateTime endTime, String type) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public VehicleOccupancy(String id, DateTime startTime, DateTime endTime, String type, String locations) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.locations = locations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
