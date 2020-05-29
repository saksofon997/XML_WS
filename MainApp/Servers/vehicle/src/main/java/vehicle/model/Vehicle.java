package vehicle.model;

import javax.persistence.*;

@Entity
public class Vehicle {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vehicle_id_seq",sequenceName="vehicle_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicle_id_seq")
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

    @Column(name = "cdw")
    private long cdw;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Pricelist pricelist;

    @Column(name = "numberOfStars")
    private int numberOfStars;

    @Column(name = "numberOfReviews")
    private int numberOfReviews;

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
                   long cdw,
                   Pricelist pricelist,
                   int numberOfStars,
                   int numberOfReviews) {
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

    public long getCdw() {
        return cdw;
    }

    public void setCdw(long cdw) {
        this.cdw = cdw;
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
}
