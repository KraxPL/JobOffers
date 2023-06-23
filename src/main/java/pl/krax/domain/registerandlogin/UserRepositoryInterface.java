package pl.krax.domain.registerandlogin;

interface UserRepositoryInterface {
    User findUserByUsername(String username);

    void saveUser(User any);
}
