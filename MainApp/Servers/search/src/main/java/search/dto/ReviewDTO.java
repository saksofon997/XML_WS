package search.dto;

import vehicle.model.Vehicle;

public class ReviewDTO {

    private Long id;
    private Long customerId;
    private Vehicle vehicle;
    private int stars;
    private String text;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Long customerId, Vehicle vehicle, int stars, String text) {
        this.id = id;
        this.customerId = customerId;
        this.vehicle = vehicle;
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
