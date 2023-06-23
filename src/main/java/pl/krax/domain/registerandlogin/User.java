package pl.krax.domain.registerandlogin;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
}
