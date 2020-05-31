package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.ReviewDTO;
import search.repository.ReviewRepo;
import search.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public List<ReviewDTO> getPending() {
        return null;
    }

    @Override
    public List<ReviewDTO> getByVehicle(Long vehicleId) {
        return null;
    }

    @Override
    public ReviewDTO add(Long vehicleId, ReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public ReviewDTO getOne(Long vehicleId, Long id) {
        return null;
    }

    @Override
    public ReviewDTO update(Long vehicleId, Long id, ReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public ReviewDTO delete(Long vehicleId, Long id) {
        return null;
    }
}
