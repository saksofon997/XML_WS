package agent.dto.shared;

public class ReviewDTO {

    private Long id;
    private Long customerId;
    private String customerName;
    private VehicleDTO vehicle;
    private int stars;
    private String text;
    private long date;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Long customerId, String customerName, VehicleDTO vehicle, int stars, String text, long date) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.stars = stars;
        this.text = text;
        this.date = date;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
