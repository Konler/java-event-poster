package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UsersAdminController {

    private final UserService userService;

    @GetMapping
    UserDto getInfoAboutUser(@RequestParam(value = "ids", required = false) List<Integer> ids,
                             @RequestParam(value = "from", defaultValue = "0", required = false) Integer from,
                             @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        log.info("Получение информации о пользователях");
        return userService.getInfoAboutUser(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Добавление нового пользователя");
        return userService.addNewUser(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Integer userId) {
        log.info("Удаление пользователя");
        userService.deleteUser(userId);
    }
}
