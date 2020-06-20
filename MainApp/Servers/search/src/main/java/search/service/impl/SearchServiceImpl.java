package search.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import saga.dto.*;
import search.dto.SearchResultPageDTO;
import search.exceptions.ConversionFailedError;
import search.model.Vehicle;
import search.repository.SearchRepo;
import search.service.SearchService;

import java.util.Arrays;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepo searchRepo;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public SearchResultPageDTO doSearch(String brand, String category, String fuel, String model,
                                        String transmission, double loc_lat, double loc_long,
                                        long startTime, long endTime,
                                        Integer pageNo, String sortKey, Boolean cdw, Long mileage,
                                        Long priceFrom, Long priceTo, Integer childSeats, Long availableMileage) throws ConversionFailedError {

        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));

        List<String> brands = brand == null ? null : Arrays.asList(brand.split(","));
        List<String> categories = category == null ? null : Arrays.asList(category.split(","));
        List<String> fuels = fuel == null ? null : Arrays.asList(fuel.split(","));
        List<String> models = model == null ? null : Arrays.asList(model.split(","));
        List<String> transmissions = transmission == null ? null : Arrays.asList(transmission.split(","));

        Page<Vehicle> pagedResult = searchRepo.getBySearchParams(brands, categories, fuels,
                models, transmissions,
                loc_lat, loc_long,
                startTime, endTime, cdw,
                mileage, priceFrom, priceTo, childSeats,
                availableMileage,
                page);

        SearchResultPageDTO pageDTO = new SearchResultPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Vehicle vehicle : pagedResult.getContent()) {
            pageDTO.getContent().add(convertToDTO(vehicle));
            System.out.println(vehicle.getImages());
        }

        return pageDTO;
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError {
        try {
            BrandDTO brandDTO = new BrandDTO(vehicle.getBrand().getId(), vehicle.getBrand().getName());
            ModelDTO modelDTO = new ModelDTO(vehicle.getModel().getId(), vehicle.getModel().getName());
            CategoryDTO categoryDTO = new CategoryDTO(vehicle.getCategory().getId(), vehicle.getCategory().getName());
            TransmissionDTO transmissionDTO = new TransmissionDTO(vehicle.getTransmission().getId(), vehicle.getTransmission().getName());
            FuelDTO fuelDTO = new FuelDTO(vehicle.getFuel().getId(), vehicle.getFuel().getName());
            PricelistDTO pricelistDTO = new PricelistDTO(vehicle.getPricelist().getId(), vehicle.getPricelist().getOwnerId(), vehicle.getPricelist().getName(),
                    vehicle.getPricelist().getPricePerDay(), vehicle.getPricelist().getPricePerKm(),
                    vehicle.getPricelist().isCdw(), vehicle.getPricelist().getDescription());

            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setId(vehicle.getId());
            vehicleDTO.setBrand(brandDTO);
            vehicleDTO.setModel(modelDTO);
            vehicleDTO.setCategory(categoryDTO);
            vehicleDTO.setTransmission(transmissionDTO);
            vehicleDTO.setFuel(fuelDTO);
            vehicleDTO.setPricelist(pricelistDTO);
            vehicleDTO.setSeats(vehicle.getSeats());
            vehicleDTO.setChildSeats(vehicle.getChildSeats());
            vehicleDTO.setMileage(vehicle.getMileage());
            vehicleDTO.setCdw(vehicle.isCdw());
            vehicleDTO.setNumberOfStars(vehicle.getNumberOfStars());
            vehicleDTO.setNumberOfReviews(vehicle.getNumberOfReviews());
            vehicleDTO.setImages(vehicle.getImages());
            vehicleDTO.setOwnerId(vehicle.getOwnerId());
            vehicleDTO.setAvailableMileage(vehicle.getAvailableMileage());
            return vehicleDTO;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }
}
