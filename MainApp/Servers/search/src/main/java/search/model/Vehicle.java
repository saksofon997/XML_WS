package search.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Where(clause="deleted=false")
public class Vehicle {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Model model;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Fuel fuel;

    @Column(name = "seats")
    private int seats;

    @Column(name = "childSeats")
    private int childSeats;

    @Column(name = "mileage")
    private long mileage;

    @Column(name = "available_mileage")
    private long availableMileage;

    @Column(name = "cdw")
    private boolean cdw;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Pricelist pricelist;

    @Column(name = "numberOfStars")
    private int numberOfStars;

    @Column(name = "numberOfReviews")
    private int numberOfReviews;

    @Column(name = "location_longitude")
    private double locationLongitude;

    @Column(name = "location_latitude")
    private double locationLatitude;

    @Column(name = "ownerId")
    private Long ownerId;

    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "images")
    List<String> images;

    @Column(name = "cid")
    private String cid;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Vehicle() {
    }

    public Vehicle(Long id,
                   Brand brand,
                   Model model,
                   Category category,
                   Transmission transmission,
                   Fuel fuel,
                   int seats,
                   int childSeats,
                   long mileage,
                   boolean cdw,
                   Pricelist pricelist,
                   int numberOfStars,
                   int numberOfReviews,
                   List<String> images,
                   double locationLongitude,
                   double locationLatitude,
                   Long owner_id,
                   long availableMileage,
                   String cid) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.transmission = transmission;
        this.fuel = fuel;
        this.seats = seats;
        this.childSeats = childSeats;
        this.mileage = mileage;
        this.cdw = cdw;
        this.pricelist = pricelist;
        this.numberOfStars = numberOfStars;
        this.numberOfReviews = numberOfReviews;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.images = images;
        this.ownerId = owner_id;
        this.availableMileage = availableMileage;
        this.cid = cid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public Pricelist getPricelist() {
        return pricelist;
    }

    public void setPricelist(Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public long getAvailableMileage() {
        return availableMileage;
    }

    public void setAvailableMileage(long availableMileage) {
        this.availableMileage = availableMileage;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
