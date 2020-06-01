package search.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.PricelistDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Pricelist;
import search.repository.PricelistRepo;
import search.repository.VehicleRepo;
import search.service.PricelistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    PricelistRepo pricelistRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public PricelistDTO convertToDTO(Pricelist pricelist) throws ConversionFailedError {
        try {
            return mapper.map(pricelist, PricelistDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Pricelist convertToModel(PricelistDTO pricelistDTO) throws ConversionFailedError {
        try {
            return mapper.map(pricelistDTO, Pricelist.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }
    @Override
    public PricelistDTO add(PricelistDTO pricelistDTO) throws ConversionFailedError, DuplicateEntity {

        Pricelist newPricelist = convertToModel(pricelistDTO);

        if (pricelistRepo.existsByName(pricelistDTO.getName()) &&
                pricelistRepo.existsByOwnerId(pricelistDTO.getOwnerId())) {
            pricelistRepo.save(newPricelist);
        } else {
            throw new DuplicateEntity("Item already exists");
        }
        return pricelistDTO;
    }

    @Override
    public PricelistDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Pricelist> pricelist = pricelistRepo.findById(id);

        if (!pricelist.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }else {
            return convertToDTO(pricelist.get());
        }
    }

    @Override
    public PricelistDTO update(Long id, PricelistDTO pricelistDTO) throws EntityNotFound, DuplicateEntity, ConversionFailedError {

        Optional<Pricelist> change = pricelistRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        if (vehicleRepo.existsByPricelist(change.get())) {
            throw new DuplicateEntity("Unable to change item");
        }
        Pricelist changed = convertToModel(pricelistDTO);
        changed.setId(id);

        pricelistRepo.save(changed);

        return pricelistDTO;
    }

    @Override
    public PricelistDTO delete(Long id) throws EntityNotFound, DuplicateEntity, ConversionFailedError {

        Optional<Pricelist> deleted = pricelistRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        } else if (vehicleRepo.existsByPricelist(deleted.get())) {
            throw new DuplicateEntity("Unable to delete item");
        }
        deleted.get().setDeleted(true);
        pricelistRepo.save(deleted.get());

        return convertToDTO(deleted.get());
    }

    @Override
    public List<PricelistDTO> getByOwner(Long ownerId) throws EntityNotFound, ConversionFailedError {

        List<Pricelist> pricelists = pricelistRepo.findAllByOwnerId(ownerId);

        if (pricelists.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<PricelistDTO> pricelistDTOS = new ArrayList<>();

        for (Pricelist p : pricelists) {
            pricelistDTOS.add(convertToDTO(p));
        }

        return pricelistDTOS;
    }
}
