package pl.krax.domain.registerandlogin;

import java.util.Optional;

interface UserRepositoryInterface {
    Optional<User> findUserByUsername(String username);

    User saveUser(User any);
}
