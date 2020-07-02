package agent.service.rental.impl;

import agent.dto.rental.RentalReportDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Rental;
import agent.model.rental.RentalReport;
import agent.repository.rental.RentalReportRepository;
import agent.repository.rental.RentalRepository;
import agent.service.rental.RentalReportService;
import agent.service.vehicle.VehicleService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalReportServiceImpl implements RentalReportService {
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    RentalReportRepository rentalReportRepository;
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    VehicleService vehicleService;

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
        vehicleService.updateMileage(rental.get().getVehicleId(), rentalReportDTO.getMileage());
        return convertToDTO(saved);
    }

}
