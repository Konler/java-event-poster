package ru.practicum.mainservice.services.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.UserDto;
import ru.practicum.mainservice.mappers.UserMapper;
import ru.practicum.mainservice.model.NewUserRequest;
import ru.practicum.mainservice.model.entities.User;
import ru.practicum.mainservice.repositories.UserRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdminServiceImpl implements UserAdminService{
    private final UserRepository userRepository;
    @Override
    public UserDto getInfoAboutUser(List<Integer> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public UserDto addNewUser(NewUserRequest newUserRequest) {
       User user= UserMapper.toUser(newUserRequest);

        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
