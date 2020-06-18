package rental.service;

import rental.dto.RentalReportDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.model.RentalReport;

public interface RentalReportService {

    RentalReportDTO convertToDTO(RentalReport bundle) throws ConversionFailedError;

    RentalReport convertToModel(RentalReportDTO rentalReportDTO) throws ConversionFailedError;

    RentalReportDTO add(RentalReportDTO rentalReportDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound;

}
