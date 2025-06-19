package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.dto.UserRequest;
import org.sopt.dto.UserResponse;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        Long id = userService.createUser(request.getName());
        UserResponse response = UserResponse.success(request.getName(), id);
        return ResponseEntity.status(201).body(response);
    }
}
