package user.service;

import user.dto.UserDTO;
import user.exceptions.*;
import user.model.User;

import java.util.List;

public interface UserService {

    UserDTO convertToDTO(User brand) throws ConversionFailedError;

    User convertToModel(UserDTO brandDTO) throws ConversionFailedError;

    UserDTO add(UserDTO brandDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError;

    UserDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    List<UserDTO> getAll() throws ConversionFailedError;

    UserDTO update(Long id, UserDTO brandDTO) throws UnexpectedError;

    UserDTO delete(Long id) throws EntityNotFound;

}
