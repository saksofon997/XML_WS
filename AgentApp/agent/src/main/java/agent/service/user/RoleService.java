package agent.service.user;

import agent.dto.user.RoleDTO;
import agent.exceptions.ConversionFailedError;
import agent.model.user.Role;
import agent.repository.user.RoleRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DozerBeanMapper mapper;

    public RoleDTO convertToDTO(Role role) throws ConversionFailedError {
        try {
            return mapper.map(role, RoleDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public Role convertToModel(RoleDTO roleDTO) throws ConversionFailedError {
        try {
            return mapper.map(roleDTO, Role.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public List<RoleDTO> getAll() throws ConversionFailedError {

        List<Role> roles = roleRepository.findAll();

        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();

        for (Role role : roles) {
            rolesDTO.add(convertToDTO(role));
        }

        return rolesDTO;
    }
}