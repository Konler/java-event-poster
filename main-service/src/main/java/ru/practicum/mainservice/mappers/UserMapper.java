package ru.practicum.mainservice.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.dto.user.UserShortDto;
import ru.practicum.mainservice.model.User;

@UtilityClass
public class UserMapper {
    public User toUser(NewUserRequest newUserRequest) {
        return User.builder()
                .email(newUserRequest.getEmail())
                .name(newUserRequest.getName())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder().id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    public UserShortDto toUserShortDto(User user){
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }
}
