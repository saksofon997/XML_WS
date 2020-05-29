package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class VehicleOccupancy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vehicleOccupancy_id_seq",sequenceName="vehicleOccupancy_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicleOccupancy_id_seq")
    private Long id;

    @Column(name = "startTime")
    private long startTime;

    @Column(name = "endTime")
    private long endTime;

    @Column(name = "type")
    private String type;

    @Column(name = "locations")
    private String locations;

    public VehicleOccupancy() {
    }

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
