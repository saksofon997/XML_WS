package vehicle.service;

import saga.dto.ReviewDTO;
import vehicle.dto.ReviewPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getPending() throws EntityNotFound, ConversionFailedError;

    List<ReviewDTO> getByVehicle(Long vehicleId) throws EntityNotFound, ConversionFailedError;

    ReviewDTO add(Long vehicleId, ReviewDTO reviewDTO) throws ConversionFailedError;

    ReviewDTO getOne(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;

    ReviewDTO update(Long vehicleId, Long id, ReviewDTO reviewDTO) throws EntityNotFound, ConversionFailedError;

    ReviewDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;

    public ReviewDTO convertToDTO(Review review) throws ConversionFailedError;

    public Review convertToModel(ReviewDTO reviewDTO) throws ConversionFailedError;

    ReviewPageDTO getAllPageable(Integer pageNo, String sort) throws ConversionFailedError;
}
