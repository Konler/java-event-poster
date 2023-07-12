package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto getInfoAboutUser(List<Integer> ids, Integer from, Integer size);

    UserDto addNewUser(NewUserRequest newUserRequest);
    void deleteUser(Integer userId);
}
