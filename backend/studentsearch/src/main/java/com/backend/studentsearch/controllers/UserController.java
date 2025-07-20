package com.backend.studentsearch.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.studentsearch.globalexceptionhandler.ResourceNotFoundException;
import com.backend.studentsearch.models.User;
import com.backend.studentsearch.repositories.UserRepository;
import com.backend.studentsearch.services.UserLoaderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Controller", description = "User management apis")
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private UserLoaderService userLoaderService;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userLoaderService.getAllUser();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Data is unavailable");
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @Operation(summary = "Get all users with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "invalid input")
    })
    @GetMapping("/load-users")
    public Page<User> loadUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("firstName"));
        return userRepository.findAll(pageRequest);
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<User>> getUserByDepartment(@RequestParam String department) {
        List<User> users = userLoaderService.getUserByDepartment(department);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Data is unavailable for department: " + department);
        } else {
            return ResponseEntity.ok(users);
        }
    }
}
