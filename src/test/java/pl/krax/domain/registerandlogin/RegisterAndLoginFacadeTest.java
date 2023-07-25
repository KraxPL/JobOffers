package pl.krax.domain.registerandlogin;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.krax.domain.registerandlogin.dto.RegisterUserDto;
import pl.krax.domain.registerandlogin.dto.RegistrationResultDto;
import pl.krax.domain.registerandlogin.dto.UserDto;

import java.util.Optional;

import static org.mockito.Mockito.*;


class RegisterAndLoginFacadeTest {

    @Mock
    private UserRepositoryInterface userRepository;

    private RegisterAndLoginFacade registerAndLoginFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registerAndLoginFacade = new RegisterAndLoginFacade(userRepository);
    }

    @Test
    void should_throw_exception_when_user_not_found() {
        // Arrange
        String username = "nonexistentUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> registerAndLoginFacade.findUserByUsername(username));
    }

    @Test
    void should_find_user_by_username() {
        // Arrange
        String username = "existingUser";
        User user = new User(1L, "name", "password", "mail@mail.com", true);
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserDto foundUserDto = registerAndLoginFacade.findUserByUsername(username);

        // Assert
        Assertions.assertEquals(user.getId(), foundUserDto.getId());
        Assertions.assertEquals(user.getUsername(), foundUserDto.getUsername());
        Assertions.assertEquals(user.getEmail(), foundUserDto.getEmail());
    }

    @Test
    void should_register_user() {
        // Arrange
        String username = "newUser";
        String password = "password";
        RegisterUserDto registerUserDto = RegisterUserDto.builder()
                .username(username)
                .password(password)
                .build();
        when(userRepository.findUserByUsername(username)).thenReturn(null);
        User savedUser = new User(1L, username, password, "mail@mail.com", true);
        when(userRepository.saveUser(any(User.class))).thenReturn(savedUser);

        // Act
        RegistrationResultDto registrationResultDto = registerAndLoginFacade.register(registerUserDto);

        // Assert
        verify(userRepository, times(1)).saveUser(any(User.class));
        Assertions.assertTrue(registrationResultDto.created());
        Assertions.assertEquals(username, registrationResultDto.username());
        Assertions.assertNotNull(registrationResultDto.id());
    }
}