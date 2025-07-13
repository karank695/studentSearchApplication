package com.backend.studentsearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.studentsearch.globalexceptionhandler.ResourceNotFoundException;
import com.backend.studentsearch.models.User;
import com.backend.studentsearch.services.UserLoaderService;


@RestController
public class UserController {
    @Autowired
    private UserLoaderService userLoaderService;
 
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userLoaderService.getAllUser();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Data is unavailable");
        } else {
            return ResponseEntity.ok(users);
        }
    }
    @GetMapping("/by-department")
    public ResponseEntity<List<User>> getUserByDepartment(@RequestParam String department) {
        List<User> users = userLoaderService.getUserByDepartment(department);
        if(users.isEmpty()){
            throw new ResourceNotFoundException("Data is unavailable for department: "+department);
        } else {
            return ResponseEntity.ok(users);
        }
    }
}
