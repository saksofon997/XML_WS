package rental.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rental.dto.BundleDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.model.Bundle;
import rental.model.Rental;
import rental.repository.BundleRepository;
import rental.service.BundleService;

import java.util.Optional;

@Service
public class BundleServiceImpl implements BundleService {

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public BundleDTO convertToDTO(Bundle bundle) throws ConversionFailedError {
        try {
            return mapper.map(bundle, BundleDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Bundle convertToModel(BundleDTO bundleDTO) throws ConversionFailedError {
        try {
            return mapper.map(bundleDTO, Bundle.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public BundleDTO add(BundleDTO bundleDTO) throws DuplicateEntity, ConversionFailedError {
        Bundle newBundle = convertToModel(bundleDTO);
        Bundle saved = bundleRepository.save(newBundle);

        return convertToDTO(saved);
    }
}
