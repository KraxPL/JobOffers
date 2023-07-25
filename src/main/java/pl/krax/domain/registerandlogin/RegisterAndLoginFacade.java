package pl.krax.domain.registerandlogin;

import lombok.AllArgsConstructor;
import pl.krax.domain.registerandlogin.dto.RegisterUserDto;
import pl.krax.domain.registerandlogin.dto.RegistrationResultDto;
import pl.krax.domain.registerandlogin.dto.UserDto;

@AllArgsConstructor
public class RegisterAndLoginFacade {
    private final UserRepositoryInterface userRepository;

    public UserDto findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public RegistrationResultDto register(RegisterUserDto registerUserDto) {
        final User user = User.builder()
                .username(registerUserDto.getUsername())
                .password(registerUserDto.getPassword())
                .build();
        User savedUser = userRepository.saveUser(user);
        return new RegistrationResultDto(savedUser.getId(), true, savedUser.getUsername());
    }
}
