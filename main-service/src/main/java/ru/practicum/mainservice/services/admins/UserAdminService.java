package ru.practicum.mainservice.services.admins;

import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.mainservice.dto.UserDto;
import ru.practicum.mainservice.model.NewUserRequest;

import java.util.List;

public interface UserAdminService {
    UserDto getInfoAboutUser(List<Integer> ids, Integer from, Integer size);

    UserDto addNewUser(NewUserRequest newUserRequest);
    void deleteUser(Integer userId);
}
