package rental.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rental.dto.RentalReportDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.EntityNotFound;
import rental.model.Rental;
import rental.model.RentalReport;
import rental.repository.RentalReportRepository;
import rental.repository.RentalRepository;
import rental.service.RentalReportService;
import saga.commands.rentalReportCommands.NewRentalReportCommand;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class RentalReportServiceImpl implements RentalReportService {
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    RentalReportRepository rentalReportRepository;
    @Autowired
    RentalRepository rentalRepository;
    @Inject
    private transient CommandGateway commandGateway;

    @Override
    public RentalReportDTO convertToDTO(RentalReport rentalReport) throws ConversionFailedError {
        try {
            return mapper.map(rentalReport, RentalReportDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public RentalReport convertToModel(RentalReportDTO rentalReportDTO) throws ConversionFailedError {
        try {
            return mapper.map(rentalReportDTO, RentalReport.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public RentalReportDTO add(RentalReportDTO rentalReportDTO) throws EntityNotFound, ConversionFailedError {
        RentalReport newReport = convertToModel(rentalReportDTO);
        Optional<Rental> rental = rentalRepository.findById(rentalReportDTO.getRentalId());
        if (!rental.isPresent()) {
            throw new EntityNotFound("Rental not found");
        }
        newReport.setRental(rental.get());

        RentalReport saved = rentalReportRepository.save(newReport);
        rental.get().setReport(saved);
        rentalRepository.save(rental.get());

        commandGateway.send(new NewRentalReportCommand(saved.getId(), saved.getRental().getVehicleId(), saved.getMileage()));

        return convertToDTO(saved);
    }

    @Override
    public void delete(Long rentalReportId) throws EntityNotFound {
        Optional<RentalReport> deleted = rentalReportRepository.findById(rentalReportId);

        if(!deleted.isPresent()) {
            throw new EntityNotFound("Items not found");
        }
        deleted.get().setDeleted(true);

        Rental rental = deleted.get().getRental();
        rental.setReport(null);
        rentalRepository.save(rental);
        rentalReportRepository.save(deleted.get());
    }


}
