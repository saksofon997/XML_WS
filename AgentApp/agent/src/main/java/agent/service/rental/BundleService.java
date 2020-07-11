package agent.service.rental;


import agent.dto.rental.BundleDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Bundle;

public interface BundleService {

    BundleDTO convertToDTO(Bundle bundle) throws ConversionFailedError;

    Bundle convertToModel(BundleDTO bundleDTO) throws ConversionFailedError;

    BundleDTO add(BundleDTO bundleDTO, boolean overMq) throws DuplicateEntity, ConversionFailedError;

    void delete(Long id) throws EntityNotFound;
}
