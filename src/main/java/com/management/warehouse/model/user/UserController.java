package com.management.warehouse.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserService userService;

    @DeleteMapping("{id}")
    public UserDto deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }

    @GetMapping("/")
    public List<UserDto> getUsers(Model model) {
        model.addAttribute("user", userService.getUserList());
        return userService.getUserList();
    }
}
