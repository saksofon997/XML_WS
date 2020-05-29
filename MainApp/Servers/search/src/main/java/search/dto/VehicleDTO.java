package search.dto;

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
    private long cdw;
    private PricelistDTO pricelist;
    private int numberOfStars;
    private int numberOfReviews;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id,
                      BrandDTO brand,
                      ModelDTO model,
                      CategoryDTO category,
                      TransmissionDTO transmission,
                      FuelDTO fuel,
                      int seats,
                      int childSeats,
                      long mileage,
                      long cdw,
                      PricelistDTO pricelist,
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

    public long getCdw() {
        return cdw;
    }

    public void setCdw(long cdw) {
        this.cdw = cdw;
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
}
