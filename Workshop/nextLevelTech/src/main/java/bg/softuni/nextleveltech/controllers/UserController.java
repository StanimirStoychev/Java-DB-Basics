package bg.softuni.nextleveltech.controllers;

import bg.softuni.nextleveltech.models.dtos.user.LoginDTO;
import bg.softuni.nextleveltech.models.dtos.user.RegisterDTO;
import bg.softuni.nextleveltech.models.entities.User;
import bg.softuni.nextleveltech.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(LoginDTO loginDTO) {
        Optional<User> user = userService.login(loginDTO);

        if (user.isPresent()) {
            return "redirect:/home";
        }

        return "user/login";
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/users/register")
    public String doRegister(RegisterDTO registerDTO) {
        if (userService.register(registerDTO)) {
            return "redirect:/users/login";
        }
        return "user/register";
    }
}
