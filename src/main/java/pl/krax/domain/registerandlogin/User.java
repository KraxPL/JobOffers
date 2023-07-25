package pl.krax.domain.registerandlogin;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@Data
class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
}
