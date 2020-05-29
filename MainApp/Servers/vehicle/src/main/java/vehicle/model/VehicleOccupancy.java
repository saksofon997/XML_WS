package vehicle.model;

import org.joda.time.DateTime;

public class VehicleOccupancy {

    private Long id;
    private long startTime;
    private long endTime;
    private String type;
    private String locations;

    public VehicleOccupancy(Long id, long startTime, long endTime, String type, String locations) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
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
