package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCid(String cid);
}
