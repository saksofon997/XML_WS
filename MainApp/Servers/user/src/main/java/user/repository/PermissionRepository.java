package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
