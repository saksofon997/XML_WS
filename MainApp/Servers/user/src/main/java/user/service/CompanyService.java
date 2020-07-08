package user.service;

import user.exceptions.ConversionFailedError;
import user.exceptions.DuplicateEntity;
import user.model.Company;

import java.util.List;

public interface CompanyService {

    Company add(Company company) throws DuplicateEntity, ConversionFailedError;

    List<Company> getAll();

}
