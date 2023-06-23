package pl.krax.domain.registerandlogin;


import com.github.tomakehurst.wiremock.admin.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.krax.domain.registerandlogin.dto.RegisterUserDto;
import pl.krax.domain.registerandlogin.dto.UserDto;

import static org.mockito.Mockito.*;

class RegisterAndLoginFacadeTest {

    @Mock
    private UserRepositoryInterface userRepository;

    private RegisterAndLoginFacade registerAndLoginFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registerAndLoginFacade = new RegisterAndLoginFacade();
    }

    @Test
    void should_throw_exception_when_user_not_found() {
        // Arrange
        String username = "nonexistentUser";
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        // Act and Assert
        Assertions.assertThrows(NotFoundException.class, () -> {
            registerAndLoginFacade.findUserByUsername(username);
        });
    }

    @Test
    void should_find_user_by_username() {
        // Arrange
        String username = "existingUser";
        User user = new User(1L, "name", "password", "mail@mail.com", true);
        when(userRepository.findUserByUsername(username)).thenReturn(user);

        // Act
        UserDto foundUserDto = registerAndLoginFacade.findUserByUsername(username);
        User foundUser = UserMapper.mapFromUserDto(foundUserDto);

        // Assert
        Assertions.assertEquals(user, foundUser);
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

        // Act
        registerAndLoginFacade.register(registerUserDto);

        // Assert
        verify(userRepository, times(1)).saveUser(any(User.class));
    }
}