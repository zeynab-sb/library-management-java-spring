package com.library.authentication.repository;

import com.library.authentication.model.Session;
import com.library.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findSessionById(String id);
    Long deleteAllByUser(User user);
}
