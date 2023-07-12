package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.mappers.UserMapper;
import ru.practicum.mainservice.model.User;
import ru.practicum.mainservice.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getInfoAboutUser(List<Integer> ids, Integer from, Integer size) {
        log.info("Пока нул");
        return null;
    }

    @Override
    public UserDto addNewUser(NewUserRequest newUserRequest) {
        User user = UserMapper.toUser(newUserRequest);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден с id"+userId)));
    }
}
