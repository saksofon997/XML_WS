package vehicle.model;

public class Vehicle {

    private String id;
    private Brand brand;
    private Model model;
    private Category category;
    private Transmission transmission;
    private Fuel fuel;
    private String seats;
    private String childSeats;
    private String mileage;
    private String cdw;
    private Pricelist pricelist;
    private String numberOfStars;
    private String numberOfReviews;

    public Vehicle(String id, Brand brand, Model model, Category category, Transmission transmission, Fuel fuel, String seats, String childSeats, String mileage, String cdw, Pricelist pricelist, String numberOfStars, String numberOfReviews) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(String childSeats) {
        this.childSeats = childSeats;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCdw() {
        return cdw;
    }

    public void setCdw(String cdw) {
        this.cdw = cdw;
    }

    public Pricelist getPricelist() {
        return pricelist;
    }

    public void setPricelist(Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public String getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(String numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
}
