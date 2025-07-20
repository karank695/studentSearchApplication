package com.backend.studentsearch.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.backend.studentsearch.models.User;
import com.backend.studentsearch.models.UserResponse;
import com.backend.studentsearch.repositories.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class UserLoaderService {
    @Value("${users.url}")
    private String url;
    @Value("${users.department}")
    private String departments;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getUserByDepartment(String department) {
        return userRepository.findByDepartment(department);
    }

    public List<String> getDepartments() {
        List<String> departmentData = Arrays.asList(departments.split(","));
        return departmentData;
    }

    @PostConstruct
    public void getUsers() {
        String[] departmentsData = departments.split(",");
        RestTemplate restTemplate = new RestTemplate();
        UserResponse response = restTemplate.getForObject(url, UserResponse.class);
        List<User> updatedUsers = response.getUsers().stream().map(user -> {
            user.setDepartment(departmentsData[(int)(Math.random() * 4)]);
            return user;
        }).collect(Collectors.toList());
        response.setUsers(updatedUsers);
        userRepository.saveAll(response.getUsers());
        System.out.println(response.getUsers());
    }
}
