package be.ucll.project;

import be.ucll.project.model.dto.CreateUserDTO;
import be.ucll.project.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testLoadUserByUsername() {
        // setup
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setUsername("Test");
        userDTO.setPassword("t");
        userService.createUser(userDTO);

        // method to be tested
        UserDetails userDetails = userService.loadUserByUsername("Test");

        // checks
        assertNotNull(userDetails);
        assertEquals("Test", userDetails.getUsername());
    }
}
