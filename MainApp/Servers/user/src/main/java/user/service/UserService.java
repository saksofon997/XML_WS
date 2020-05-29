package user.service;

import user.dto.UserDTO;
import user.dto.UserPageDTO;
import user.exceptions.*;
import user.model.User;

import java.util.List;

public interface UserService {

    UserDTO convertToDTO(User brand) throws ConversionFailedError;

    User convertToModel(UserDTO brandDTO) throws ConversionFailedError;

    UserDTO add(UserDTO brandDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError;

    UserDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    UserPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError;

    UserDTO update(Long id, UserDTO brandDTO) throws EntityNotFound, ConversionFailedError;

    UserDTO activateOrDeactivate(Long id, UserDTO userDTO) throws EntityNotFound, ConversionFailedError;

    UserDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

}
