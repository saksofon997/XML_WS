package agent.service.user.impl;

import agent.dto.user.RoleDTO;
import agent.dto.user.UserDTO;
import agent.dto.user.UserPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.InvalidEmailOrPasswordError;
import agent.model.user.Role;
import agent.model.user.User;
import agent.repository.user.RoleRepository;
import agent.repository.user.UserRepository;
import agent.service.user.UserService;
import agent.utils.Requests;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public UserDTO convertToDTO(User user) throws ConversionFailedError {
        try {
            UserDTO dto = mapper.map(user, UserDTO.class);
            dto.setPassword(null);
            return dto;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public User convertToModel(UserDTO userDTO) throws ConversionFailedError {
        try {
            return mapper.map(userDTO, User.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    public Role convertToModelRole(RoleDTO roleDTO) throws ConversionFailedError {
        try {
            return mapper.map(roleDTO, Role.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public UserDTO add(UserDTO userDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError, IOException {
        if (!isValidEmailAddress(userDTO.getEmail())){
            throw new InvalidEmailOrPasswordError("Email is invalid");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().equals("")){
            throw new InvalidEmailOrPasswordError("Password is invalid");
        }

        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()){
            return userRegistration(userDTO);
        } else {
            return userCreationByAdmin(userDTO);
        }
    }

    private UserDTO userRegistration(UserDTO userDTO) throws DuplicateEntity, ConversionFailedError, IOException {
        User check = userRepository.findByEmail(userDTO.getEmail());
        if (check != null){
            throw new DuplicateEntity("User with this email already exists");
        }

        User user = convertToModel(userDTO);
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("SIMPLE_USER"));
        roles.add(roleRepository.findByName("ROLE_VEHICLE_OWNER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setEnabled(true);

        Requests.sendEmail(userDTO.getEmail(), "Successful registration to Car2Go.");

        User registered = userRepository.save(user);
        return convertToDTO(registered);
    }

    private UserDTO userCreationByAdmin(UserDTO userDTO) throws DuplicateEntity, ConversionFailedError, IOException {
        User check = userRepository.findByEmail(userDTO.getEmail());
        if (check != null){
            throw new DuplicateEntity("User with this email already exists");
        }

        User user = convertToModel(userDTO);
        Set<Role> roles = new HashSet<Role>();
        for (RoleDTO roleDTO: userDTO.getRoles()){
            Optional<Role> role = roleRepository.findById(roleDTO.getId());
            role.ifPresent(roles::add);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);

        Requests.sendEmail(userDTO.getEmail(), "Successful registration to Car2Go.");

        User registered = userRepository.save(user);
        return convertToDTO(registered);
    }

    @Override
    public UserDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return convertToDTO(user.get());
        } else {
            throw new EntityNotFound("User with this email already exists");
        }
    }

    @Override
    public UserPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError {

        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        Page<User> pagedResult = userRepository.findAll(page);

        UserPageDTO pageDTO = new UserPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (User user: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(user));
        }

        return pageDTO;
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) throws EntityNotFound, ConversionFailedError, IOException {
        Optional<User> check = userRepository.findById(id);
        if (!check.isPresent() || !id.equals(userDTO.getId())){
            throw new EntityNotFound("User not found, invalid data");
        }
        User user = check.get();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setState(userDTO.getState());

        Set<Role> newRoles = new HashSet<Role>();
        for(RoleDTO rdto : userDTO.getRoles()) {
            newRoles.add(convertToModelRole(rdto));
        }

        user.setRoles(newRoles);

        Requests.sendEmail(userDTO.getEmail(), "Successfuly updated user account");

        return convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO activateOrDeactivate(Long id) throws EntityNotFound, ConversionFailedError, IOException {
        Optional<User> check = userRepository.findById(id);
        if (!check.isPresent()){
            throw new EntityNotFound("User not found, invalid data");
        }
        User user = check.get();
        user.setEnabled(!user.isEnabled());

        Requests.sendEmail(user.getEmail(), "User status changed");

        return convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO delete(Long id) throws EntityNotFound, ConversionFailedError, IOException {
        Optional<User> check = userRepository.findById(id);
        if (!check.isPresent()){
            throw new EntityNotFound("User not found, invalid data");
        }
        User user = check.get();
        user.setDeleted(true);

        Requests.sendEmail(user.getEmail(), "User account deleted");

        return convertToDTO(userRepository.save(user));
    }

    public static boolean isValidEmailAddress(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
