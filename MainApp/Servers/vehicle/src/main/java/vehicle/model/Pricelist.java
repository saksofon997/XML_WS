package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pricelist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="pricelist_id_seq",sequenceName="pricelist_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pricelist_id_seq")
    private Long id;

    @Column(name = "owner")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "pricePerDay")
    private long pricePerDay;

    @Column(name = "pricePerKm")
    private long pricePerKm;

    @Column(name = "cdw")
    private long cdw;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "pricelist", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;

    public Pricelist() {
    }

    public Pricelist(Long id,
                     Long ownerId,
                     String name,
                     long pricePerDay,
                     long pricePerKm,
                     long cdw,
                     String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.cdw = cdw;
        this.description = description;
    }

    public Pricelist(Long id,
                     Long ownerId,
                     String name,
                     long pricePerDay,
                     long pricePerKm,
                     long cdw,
                     String description,
                     List<Vehicle> vehicles) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.cdw = cdw;
        this.description = description;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public long getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(long pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public long getCdw() {
        return cdw;
    }

    public void setCdw(long cdw) {
        this.cdw = cdw;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
