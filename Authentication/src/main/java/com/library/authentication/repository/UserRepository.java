package com.library.authentication.repository;

import com.library.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

//    @Query("SELECT User.id, User.username, User.enabled, User.authority, User.password FROM User AS usr INNER JOIN Session AS sess WHERE sess.id = ?1")
//    List<User> FindUserBySessionId(String id);
}
