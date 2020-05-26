package vehicle.service;

import vehicle.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getPending();
}
