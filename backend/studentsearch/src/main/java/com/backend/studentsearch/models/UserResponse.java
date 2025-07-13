package com.backend.studentsearch.models;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserResponse {
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
