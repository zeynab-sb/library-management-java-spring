package com.library.main.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "text", nullable = false)
    private String text;

    @CreationTimestamp
    @Column(name = "createDateTime")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;

    public Comment(User user, Book book, String text) {
        this.user = user;
        this.book = book;
        this.text = text;
    }

    public Comment() {

    }
}
