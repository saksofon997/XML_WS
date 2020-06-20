package agent.service.rental;

import agent.dto.rental.RentalReportDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.RentalReport;

public interface RentalReportService {

    RentalReportDTO convertToDTO(RentalReport bundle) throws ConversionFailedError;

    RentalReport convertToModel(RentalReportDTO rentalReportDTO) throws ConversionFailedError;

    RentalReportDTO add(RentalReportDTO rentalReportDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound;

}
