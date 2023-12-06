package com.proyectoreserva.proyectoreserva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getUser")
    public List<Users> getUser() {
        return this.userService.getUser();
    }

    @PostMapping(path = "newUser")
    public void newUser(@RequestBody Users user) {
        this.userService.newUser(user);
    }

}
