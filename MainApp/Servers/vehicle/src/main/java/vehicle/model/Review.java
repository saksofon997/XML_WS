package vehicle.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="pricelist_id_seq",sequenceName="pricelist_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pricelist_id_seq")
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @Column(name = "stars")
    private int stars;

    @Column(name = "text")
    private String text;

    public Review() {
    }

    public Review(Long id, Long customerId, Vehicle vehicle, int stars, String text) {
        this.id = id;
        this.customerId = customerId;
        this.vehicle = vehicle;
        this.stars = stars;
        this.text = text;
    }

    public Review(Long id, Long customerId, int stars, String text) {
        this.id = id;
        this.customerId = customerId;
        this.stars = stars;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
