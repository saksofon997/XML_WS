package rental.service;

import rental.dto.BundleDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.model.Bundle;

public interface BundleService {

    BundleDTO convertToDTO(Bundle bundle) throws ConversionFailedError;

    Bundle convertToModel(BundleDTO bundleDTO) throws ConversionFailedError;

    BundleDTO add(BundleDTO bundleDTO) throws DuplicateEntity, ConversionFailedError;

    void delete(Long id) throws EntityNotFound;
}
