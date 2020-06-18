package saga.dto;

import java.util.List;

public class VehicleDTO {

    private Long id;
    private BrandDTO brand;
    private ModelDTO model;
    private CategoryDTO category;
    private TransmissionDTO transmission;
    private FuelDTO fuel;
    private int seats;
    private int childSeats;
    private long mileage;
    private boolean cdw;
    private PricelistDTO pricelist;
    private int numberOfStars;
    private int numberOfReviews;
    private double locationLongitude;
    private double locationLatitude;
    private List<String> images;
    private Long ownerId;
    private long availableMileage;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, BrandDTO brand, ModelDTO model, CategoryDTO category,
                      TransmissionDTO transmission, FuelDTO fuel, int seats, int childSeats,
                      long mileage, boolean cdw, PricelistDTO pricelist, int numberOfStars,
                      int numberOfReviews, double locationLongitude, double locationLatitude,
                      List<String> images, Long ownerId, long availableMileage) {
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
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.images = images;
        this.ownerId = ownerId;
        this.availableMileage = availableMileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public TransmissionDTO getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionDTO transmission) {
        this.transmission = transmission;
    }

    public FuelDTO getFuel() {
        return fuel;
    }

    public void setFuel(FuelDTO fuel) {
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

    public PricelistDTO getPricelist() {
        return pricelist;
    }

    public void setPricelist(PricelistDTO pricelist) {
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
}
