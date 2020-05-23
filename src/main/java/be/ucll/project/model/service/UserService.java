package be.ucll.project.model.service;

import be.ucll.project.model.dto.CreateUserDTO;
import be.ucll.project.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(CreateUserDTO userDTO);
}
