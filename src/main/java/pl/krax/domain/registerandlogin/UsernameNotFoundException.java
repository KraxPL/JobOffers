package pl.krax.domain.registerandlogin;

class UsernameNotFoundException extends RuntimeException {
    UsernameNotFoundException(String message) {
        super(message);
    }
}