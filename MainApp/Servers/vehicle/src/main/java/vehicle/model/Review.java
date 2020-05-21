package vehicle.model;

public class Review {

    private String id;
    private String customerId;
    private Vehicle vehicle;
    private String stars;
    private String text;

    public Review(String id, String customerId, Vehicle vehicle, String stars, String text) {
        this.id = id;
        this.customerId = customerId;
        this.vehicle = vehicle;
        this.stars = stars;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
