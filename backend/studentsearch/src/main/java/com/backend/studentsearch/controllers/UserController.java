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
    public ResponseEntity<List<User>> getUsers(@RequestParam String department) {
        List<User> userList;
        if (!(department != null) || !department.isEmpty()) {
            userList = userLoaderService.getUserByDepartment(department);
        } else {
            userList = userLoaderService.getAllUser();
        }
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("Data not found");
        } 
        return ResponseEntity.ok(userList);
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

    @GetMapping("/departments")
    public ResponseEntity<List<String>> getDepartments() {
        List<String> departments = userLoaderService.getDepartments();
        if (departments.isEmpty()) {
            throw new ResourceNotFoundException("No department found");
        }
        return ResponseEntity.ok(departments);
    }
}
