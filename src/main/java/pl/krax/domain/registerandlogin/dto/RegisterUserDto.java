package pl.krax.domain.registerandlogin.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterUserDto {
    private String username;
    private String password;
}
