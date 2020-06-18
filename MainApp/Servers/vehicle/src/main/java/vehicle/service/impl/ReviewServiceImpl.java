package vehicle.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import saga.commands.TypeOfCommand;
import saga.commands.priceListCommands.MainPriceListCommand;
import saga.commands.reviewCommands.MainReviewCommand;
import saga.dto.PricelistDTO;
import saga.dto.ReviewDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.dto.ReviewPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.*;
import vehicle.repository.ReviewRepo;
import vehicle.repository.VehicleRepo;
import vehicle.service.ReviewService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Inject
    private transient CommandGateway commandGateway;

    @Override
    public ReviewDTO convertToDTO(Review review) throws ConversionFailedError {
        try {
            return mapper.map(review, ReviewDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Review convertToModel(ReviewDTO reviewDTO) throws ConversionFailedError {
        try {
            return mapper.map(reviewDTO, Review.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public ReviewPageDTO getAllPageable(Integer pageNo, String sort) throws ConversionFailedError {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sort));
        Page<Review> pagedResult = reviewRepo.findAllByStatusEquals(page, ReviewStatus.PENDING);

        ReviewPageDTO pageDTO = new ReviewPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Review review: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(review));
        }

        return pageDTO;
    }

    @Override
    public List<ReviewDTO> getPending() throws EntityNotFound, ConversionFailedError {

        List<Review> reviews = reviewRepo.findByStatusEquals(ReviewStatus.PENDING);

        return getReviewDTOS(reviews);
    }

    @Override
    public List<ReviewDTO> getByVehicle(Long vehicleId) throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        List<Review> reviews = reviewRepo.findByVehicleAndStatusEquals(vehicle.get(), ReviewStatus.PUBLISHED);

        return getReviewDTOS(reviews);
    }


    @Override
    public ReviewDTO add(Long vehicleId, ReviewDTO reviewDTO) throws ConversionFailedError {

        Review newReview = convertToModel(reviewDTO);
        newReview.setStatus(ReviewStatus.PENDING);

        Review savedReview = reviewRepo.save(newReview);
        commandGateway.send(new MainReviewCommand(savedReview.getId(),vehicleId, reviewDTO, TypeOfCommand.CREATE));

        return reviewDTO;
    }

    @Override
    public ReviewDTO getOne(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        Optional<Review> review = reviewRepo.findByIdAndVehicle(id, vehicle.get());

        if(!review.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        return convertToDTO(review.get());
    }

    @Override
    public ReviewDTO update(Long vehicleId, Long id, ReviewDTO reviewDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        Optional<Review> review = reviewRepo.findByIdAndVehicle(id, vehicle.get());

        if(!review.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        review.get().setStatus(ReviewStatus.PUBLISHED);

        Review savedReview = reviewRepo.save(review.get());
        commandGateway.send(new MainReviewCommand(savedReview.getId(),vehicleId, reviewDTO, TypeOfCommand.UPDATE));

        return reviewDTO;
    }

    @Override
    public ReviewDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        if(!vehicle.isPresent()) {
            throw new EntityNotFound("Items not found");
        }

        Optional<Review> deleted = reviewRepo.findByIdAndVehicle(id, vehicle.get());

        if(!deleted.isPresent()) {
            throw new EntityNotFound("Items not found");
        }
        deleted.get().setDeleted(true);
        reviewRepo.save(deleted.get());
        // reviewRepo.deleteById(id);
        commandGateway.send(new MainReviewCommand(deleted.get().getId(),vehicleId, convertToDTO(deleted.get()), TypeOfCommand.DELETE));
        return convertToDTO(deleted.get());
    }

    private List<ReviewDTO> getReviewDTOS(List<Review> reviews) throws EntityNotFound, ConversionFailedError {
        if (reviews.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        for (Review r : reviews) {
            reviewDTOS.add(convertToDTO(r));
        }

        return reviewDTOS;
    }
}
