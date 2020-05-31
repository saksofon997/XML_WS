package vehicle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.model.Brand;
import vehicle.model.Model;

import java.util.ArrayList;

public interface ModelRepo extends JpaRepository<Model, Long> {
    Model findByName(String name);
    ArrayList<Model> getAllByBrand(Brand brand);
}
