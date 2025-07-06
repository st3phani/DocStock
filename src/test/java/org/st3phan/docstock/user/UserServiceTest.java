package org.st3phan.docstock.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.st3phan.docstock.user.dto.UserCreateRequest;
import org.st3phan.docstock.user.dto.UserCreateResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private UserCreateRequest request;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new UserCreateRequest();
        request.setNume("Ion");
        request.setPrenume("Popescu");
        request.setUsername("ion");
        request.setEmail("ion@example.com");
        request.setPassword("pass");

        user = new User();
        user.setId(1L);
        user.setNume(request.getNume());
        user.setPrenume(request.getPrenume());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword("encoded");
    }

    @Test
    void registerUser_success() {
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(new UserCreateResponse());

        UserCreateResponse response = userService.registerUser(request);
        assertNotNull(response);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_usernameExists() {
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(request));
    }

    @Test
    void registerUser_emailExists() {
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(request));
    }
}
