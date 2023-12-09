package com.proyectoreserva.proyectoreserva.user;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

   @Operation(summary = "Get a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class))),
            @ApiResponse(responseCode = "404", description = "No users found",
                    content = @Content),
    })


    @GetMapping(path = "getUser")
    public List<Users> getUser() {
        return this.userService.getUser();
    }

    @PostMapping(path = "newUser")
    public ResponseEntity<Object>  newUser(@RequestBody Users user) {
       return this.userService.newUser(user);
    }

    @PutMapping (path = "updateUser")
    public ResponseEntity<Object>  updateUser(@RequestBody Users user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object>  deleteUser(@PathVariable ("Id")  Integer id) {
        return this.userService.deleteUser(id);
    }



}
