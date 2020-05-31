package vehicle.service;

import vehicle.dto.ReviewDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getPending() throws EntityNotFound, ConversionFailedError;

    List<ReviewDTO> getByVehicle(Long vehicleId) throws EntityNotFound, ConversionFailedError;

    ReviewDTO add(Long vehicleId, ReviewDTO reviewDTO) throws ConversionFailedError;

    ReviewDTO getOne(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;

    ReviewDTO update(Long vehicleId, Long id, ReviewDTO reviewDTO) throws EntityNotFound, ConversionFailedError;

    ReviewDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;
}
