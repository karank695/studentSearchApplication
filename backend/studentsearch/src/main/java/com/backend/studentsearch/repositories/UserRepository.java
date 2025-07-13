package com.backend.studentsearch.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.studentsearch.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByDepartment(String department);

   @Query("SELECT u FROM User u WHERE u.firstName LIKE %:str%")
   List<User> findNameContains(@Param("str") String str);
}
