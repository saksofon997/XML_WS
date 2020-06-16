package agent.service.user;

import agent.dto.user.UserDTO;
import agent.dto.user.UserPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.InvalidEmailOrPasswordError;
import agent.model.user.User;

public interface UserService {

    UserDTO convertToDTO(User user) throws ConversionFailedError;

    User convertToModel(UserDTO userDTO) throws ConversionFailedError;

    UserDTO add(UserDTO userDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError;

    UserDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    UserPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError;

    UserDTO update(Long id, UserDTO userDTO) throws EntityNotFound, ConversionFailedError;

    UserDTO activateOrDeactivate(Long id, UserDTO userDTO) throws EntityNotFound, ConversionFailedError;

    UserDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

}
