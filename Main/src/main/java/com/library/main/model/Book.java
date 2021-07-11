package com.library.main.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
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

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    @Column(name = "keywords")
    private String keywords;

    @CreationTimestamp
    @Column(name = "createDateTime")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;
}
