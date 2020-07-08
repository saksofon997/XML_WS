package user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.exceptions.ConversionFailedError;
import user.exceptions.DuplicateEntity;
import user.exceptions.EntityNotFound;
import user.model.Company;
import user.repository.CompanyRepository;
import user.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company add(Company company) throws DuplicateEntity, ConversionFailedError {
        if (company.getCid() == null) {
            throw new ConversionFailedError("Invalid CID");
        }
        Company check = companyRepository.findByCid(company.getCid());
        if (check != null) {
            throw new DuplicateEntity("Company with given CID already exists");
        }

        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
