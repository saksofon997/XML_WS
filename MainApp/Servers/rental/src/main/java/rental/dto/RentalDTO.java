package rental.dto;

import java.util.Objects;

public class RentalDTO {

    private Long id;
    private Long vehicleId;
    private Long customerId;
    private Long ownerId;
    private long startTime;
    private long endTime;
    private BundleDTO bundle;
    private RentalStatus status;
    private RentalReportDTO report;
    private String cid;

    public RentalDTO(Long id, Long vehicleId, Long customerId, Long ownerId, long startTime, long endTime, BundleDTO bundle, RentalStatus status, RentalReportDTO report, String cid) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.ownerId = ownerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bundle = bundle;
        this.status = status;
        this.report = report;
        this.cid = cid;
    }

    public RentalDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public BundleDTO getBundle() {
        return bundle;
    }

    public void setBundle(BundleDTO bundle) {
        this.bundle = bundle;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public RentalReportDTO getReport() {
        return report;
    }

    public void setReport(RentalReportDTO report) {
        this.report = report;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", customerId=" + customerId +
                ", ownerId=" + ownerId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bundle=" + bundle +
                ", status=" + status +
                ", report=" + report +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDTO rentalDTO = (RentalDTO) o;
        return getStartTime() == rentalDTO.getStartTime() &&
                getEndTime() == rentalDTO.getEndTime() &&
                Objects.equals(getId(), rentalDTO.getId()) &&
                Objects.equals(getVehicleId(), rentalDTO.getVehicleId()) &&
                Objects.equals(getCustomerId(), rentalDTO.getCustomerId()) &&
                Objects.equals(getOwnerId(), rentalDTO.getOwnerId()) &&
                Objects.equals(getBundle(), rentalDTO.getBundle()) &&
                getStatus() == rentalDTO.getStatus() &&
                Objects.equals(getReport(), rentalDTO.getReport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVehicleId(), getCustomerId(), getOwnerId(), getStartTime(), getEndTime(), getBundle(), getStatus(), getReport());
    }
}
