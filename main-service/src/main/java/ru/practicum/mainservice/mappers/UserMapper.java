package ru.practicum.mainservice.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.model.NewUserRequest;
import ru.practicum.mainservice.model.entities.User;
@UtilityClass
public class UserMapper {
    public User toUser(NewUserRequest newUserRequest){
        return User.builder()
                .email(newUserRequest.getEmail())
                .name(newUserRequest.getName())
                .build();
    }
}
