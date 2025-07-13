package com.backend.studentsearch.models;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
