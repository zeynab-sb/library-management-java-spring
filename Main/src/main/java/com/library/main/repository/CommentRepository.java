package com.library.main.repository;


import com.library.main.model.Book;
import com.library.main.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook(Book book);
}
