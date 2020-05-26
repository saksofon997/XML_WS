package vehicle.service;

import vehicle.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getPending();

    List<ReviewDTO> getByVehicle(Long vehicleId);

    ReviewDTO add(Long vehicleId, ReviewDTO reviewDTO);

    ReviewDTO getOne(Long vehicleId, Long id);

    ReviewDTO update(Long vehicleId, Long id, ReviewDTO reviewDTO);

    ReviewDTO delete(Long vehicleId, Long id);
}
