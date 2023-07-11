package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.UserDto;
import ru.practicum.mainservice.model.NewUserRequest;
import ru.practicum.mainservice.services.admins.UserAdminService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UsersAdminController {
    private final UserAdminService userAdminService;

    @GetMapping
    UserDto getInfoAboutUser(@RequestParam(value = "ids", required = false) List<Integer> ids,
                             @RequestParam(value = "from", defaultValue = "0", required = false) Integer from,
                             @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        log.info("Получение информации о пользователях");
        return userAdminService.getInfoAboutUser(ids, from, size);
    }

    @PostMapping
    UserDto addNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {

        log.info("Добавление нового пользователя");
        return userAdminService.addNewUser(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Integer userId) {
        log.info("Удаление пользователя");
        userAdminService.deleteUser(userId);
    }
}
