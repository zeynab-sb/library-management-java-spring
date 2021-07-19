package com.library.main.repository;

import com.library.main.model.Book;
import com.library.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByPublisher(User publisher);
}
