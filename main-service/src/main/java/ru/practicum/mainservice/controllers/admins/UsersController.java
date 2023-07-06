package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.NewUserRequest;
import ru.practicum.mainservice.model.UserDto;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UsersController {
    @GetMapping
    UserDto getInfoAboutUser(@RequestParam("ids") List<Integer> ids,
                             @RequestParam("from") Integer from,
                             @RequestParam("size") Integer size) {
        return null;
    }

    @PostMapping
    UserDto addNewUser(@RequestBody NewUserRequest newUserRequest) {
        return null;
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Integer userId) {
    }
}
