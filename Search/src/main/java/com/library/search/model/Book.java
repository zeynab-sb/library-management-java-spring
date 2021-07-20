package com.library.search.model;

import com.library.search.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ISSN", nullable = false)
    private long ISSN;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writers", nullable = false)
    private String writers;

//    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private String publisher;

    @Column(name = "date", nullable = false)
    private String date;

    //@Lob
    @Column(name = "photo")
    private String photo;

    @Column(name = "keywords")
    private String keywords;

    @CreationTimestamp
    @Column(name = "createDateTime")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;

    public Book(long ISSN, String title, String writers, String publisher, String date, String photo, String keywords) {
        this.ISSN = ISSN;
        this.title = title;
        this.writers = writers;
        this.publisher = publisher;
        this.date = date;
        this.photo = photo;
        this.keywords = keywords;
    }

    public Book() {

    }

    public Book(int id, String title) {
        this.id = id ;
        this.title = title ;
    }
}
