package vehicle.dto;

import org.springframework.web.multipart.MultipartFile;
import saga.dto.VehicleDTO;

import java.util.List;

public class FullVehicleDTO {
    private VehicleDTO vehicleDTO;
    private List<MultipartFile> images;

    public FullVehicleDTO(VehicleDTO vehicleDTO, List<MultipartFile> images) {
        this.vehicleDTO = vehicleDTO;
        this.images = images;
    }

    public FullVehicleDTO() {
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
