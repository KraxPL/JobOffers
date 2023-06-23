package pl.krax.domain.registerandlogin.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String email;
}
