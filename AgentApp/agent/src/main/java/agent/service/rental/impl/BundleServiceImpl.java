package agent.service.rental.impl;

import agent.dto.rental.BundleDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Bundle;
import agent.repository.rental.BundleRepository;
import agent.service.rental.BundleService;
import agent.soap.RentalClient;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BundleServiceImpl implements BundleService {

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    RentalClient rentalClient;

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
    public BundleDTO add(BundleDTO bundleDTO, boolean overMq) throws DuplicateEntity, ConversionFailedError {
        Bundle newBundle = convertToModel(bundleDTO);
        Bundle saved = bundleRepository.save(newBundle);
        BundleDTO savedDTO = convertToDTO(saved);
        if (!overMq) {
            rentalClient.addBundle(mapper.map(savedDTO, agent.soap.gen.BundleDTO.class));
        }
        return convertToDTO(saved);
    }

    @Override
    public void delete(Long id) throws EntityNotFound {
        Optional<Bundle> bundle = bundleRepository.findById(id);
        if(!bundle.isPresent()) {
            throw new EntityNotFound("Bundle not found");
        }

        bundle.get().setDeleted(true);
        bundleRepository.save(bundle.get());
    }
}
