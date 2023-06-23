package pl.krax.domain.registerandlogin;

import pl.krax.domain.registerandlogin.dto.UserDto;

class UserMapper {
    public static UserDto mapFromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static User mapFromUserDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
