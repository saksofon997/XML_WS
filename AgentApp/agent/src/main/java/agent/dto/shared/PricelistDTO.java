package agent.dto.shared;

public class PricelistDTO {

    private Long id;
    private Long ownerId;
    private String name;
    private long pricePerDay;
    private long pricePerKm;
    private boolean cdw;
    private String description;
    private double discount;
    private double penalty;

    public PricelistDTO() {
    }

    public PricelistDTO(Long id,
                        Long ownerId,
                        String name,
                        long pricePerDay,
                        long pricePerKm,
                        boolean cdw,
                        String description,
                        double discount,
                        double penalty) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.cdw = cdw;
        this.description = description;
        this.discount = discount;
        this.penalty = penalty;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
