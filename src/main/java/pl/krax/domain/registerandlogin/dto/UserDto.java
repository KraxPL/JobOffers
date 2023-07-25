package pl.krax.domain.registerandlogin.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {
    private Long id;
    private String username;
    private String email;
}
