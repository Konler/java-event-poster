package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.user.NewUserRequest;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UsersAdminController {

    private final UserService userService;

    @GetMapping//Есть
    List<UserDto> getInfoAboutUser(@RequestParam(value = "ids",required = false) List<Integer> ids,
                                   @PositiveOrZero @RequestParam(value = "from", defaultValue = "0") Integer from,
                                   @PositiveOrZero @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("Получение информации о пользователях");
        return userService.getInfoAboutUser(ids, from, size);
    }

    @PostMapping//есть
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Добавление нового пользователя");
        return userService.addNewUser(newUserRequest);
    }

    @DeleteMapping("/{userId}")//есть
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Integer userId) {
        log.info("Удаление пользователя");
        userService.deleteUser(userId);
    }
}
